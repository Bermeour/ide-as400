package com.ide.automation.setting;

import com.ide.as400.Session5250;
import com.ide.as400.SessionConfig;
import com.ide.as400.SessionPanel;
import com.ide.as400.TN5250jConstants;
import com.ide.as400.framework.tn5250.Screen5250;
import com.ide.as400.interfaces.ConfigureFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Properties;

import static com.ide.as400.tools.LangTool.init;

public class TerminalConfiguraction {

    private JFrame frame;
    private JPanel mainPanel;
    private SessionPanel sessionGui;
    public Session5250 session;
    public SessionBean sessionBean;
    private Screen5250 screen;
    private final String host;
    private final String port;
    private final String screenSize;
    private final String headless;
    private final long screenTimeOut;
    private final String SSL_PROTOCOL="TLS";
    public boolean connetcted;
    public boolean informed;
    public int informChange;
    private static final int TIME_SLEEP_1000=1000;
    private static final int TIME_SLEEP_500=500;
    private static final int TIME_SLEEP_MULTIPLIER_5=5;

    public TerminalConfiguraction(){
        ConfigProperties config = new ConfigProperties();
        this.host=config.getHost();
        this.port=config.getPort();
        this.screenSize=config.getScreenSize();
        this.headless=config.getHeadless();
        this.screenTimeOut=config.getScreenTimeout();
    }

    private void initSession(){
        ConfigureFactory.getInstance();
        init();
        Properties properties= new Properties();
        SessionConfig config= new SessionConfig(host,"Nombre de as400 compañia");
        config.setProperty("font","Consolas");

        properties.put(TN5250jConstants.SESSION_TN_ENHANCED,"1");
        properties.put(TN5250jConstants.SESSION_HOST_PORT,port);
        //properties.put(TN5250jConstants.SSL_TYPE,SSL_PROTOCOL);// este valor debe de ser parametrizable desde el properties o otro lugar
        properties.put(TN5250jConstants.SESSION_SCREEN_SIZE,screenSize);
        session= new Session5250(properties,host,"nombre del as400",config);
        session.addSessionListener(changeEvent -> connetcted=changeEvent.getState()==TN5250jConstants.STATE_CONNECTED);

        sessionBean = new SessionBean(session);
        try {
            sessionBean.setHost(host);
        }catch (UnknownHostException e){
            throw new RuntimeException(e);
        }
        //Codigo para idioma
        sessionBean.setCodePage("Cp284");
        sessionBean.setNoSaveConfigFile();
        sessionBean.setDeviceName("FwAS400");
        screen = session.getScreen();
        screen.getOIA().addOIAListener((oia, change) -> informed = informed | informChange ==change);
        System.out.println("Iniciando comunicacion con mi Host....");

    }

    public void initFrame(){
        frame = new JFrame("IBM AS400 NOMBRE");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setAlwaysOnTop(false);
//Revisar comportamiento
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sessionBean.signOff();
                sessionBean.disconnect();
            }
        });
    }
/* muestra el contenido de la terminal sobre el frame y lo hace visible , ademas inicia la concxion*/
    public void initPanel(){
        sessionGui = new SessionPanel(sessionBean.getSession());
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(sessionGui,BorderLayout.CENTER);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        try{
            Thread.sleep(TIME_SLEEP_1000);
            frame.setAutoRequestFocus(true);
            sessionBean.connect();
            Thread.sleep(TIME_SLEEP_1000);
            int count =0;
            while (!sessionBean.isConnected()){
                if(count<TIME_SLEEP_MULTIPLIER_5){
                    count++;
                    Thread.sleep(TIME_SLEEP_500);
                }else {
                    throw new ConnectException("Can´t connect to Host "+ host);
                }
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }catch (ConnectException e){
            throw  new RuntimeException(e);
        }
    }
    //inicia la terminal y ñla conexion
    public void startTerminal(){
        initSession();
        initFrame();
        initPanel();
    }
    //contiene la instancia de la terminal
public Screen5250 hostSpace(){return screen;}

/*se encarga de cerrar la conexion tanto con la terminal como con el host*/
public void disconnectHot() {
    try {
        sessionBean.signOff();
        sessionBean.disconnect();
        sessionBean.closeDown();
        mainPanel.removeAll();
        int safeCount = 0;
        try {
            Thread.sleep(TIME_SLEEP_1000);
            while (sessionBean.isConnected()) {
                if (safeCount >= 5) {
                    break;
                }
                Thread.sleep(TIME_SLEEP_500);
                safeCount++;
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        frame.dispose();
    } catch (Exception e) {
        System.out.println("Hilo Terminado.");
    }
}

}
