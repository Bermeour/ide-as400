package com.ide.automation.setting;

import com.ide.as400.Session5250;
import com.ide.as400.SessionConfig;
import com.ide.as400.SessionPanel;
import com.ide.as400.keyboard.KeyMnemonic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

import static com.ide.as400.TN5250jConstants.*;

public class SessionBean extends SessionPanel {

    private static final long serialVersionUID=1L;
    private Dimension preferredSize;
    private final Properties sessionProperties;
    private boolean embeddedSignOn;
    private String user;
    private String password;
    private String library;
    private String menu;
    private String program;
    private String initialCommand;
    private String afterSignOn;
    private int visibilityInterval;

    //=======================================================
    // Constructors
    //=============================
    public SessionBean(String configurationResource, String sessionName) {
        this(new Properties(),configurationResource,sessionName);
    }

    public SessionBean(Properties sessionProperties,String configurationResource, String sessionName) {
        this(new Session5250(sessionProperties,null,sessionName,new SessionConfig(configurationResource,sessionName)));
    }

    public SessionBean(Session5250 session) {
        super(session);
        this.sessionProperties=session.getConnectionProperties();
        this.sessionProperties.put(SESSION_LOCALE, Locale.getDefault());
        this.getSession().getConfiguration().addSessionConfigListener(this);
    }
    //=======================================================
    // Implementacion de emulador
    //=============================

    public void setHost(String host) throws UnknownHostException ,IllegalStateException{
    failIfConnected();
    this.setIpAddress(InetAddress.getByName(host).getHostAddress());
    }

    public void setIpAddress(String ipAddress)throws IllegalStateException{
        failIfConnected();
        if(isSignificant(ipAddress)){
            sessionProperties.put(SESSION_HOST,ipAddress);
        }
    }

    public void setIPPort(int ipPort){
        failIfConnected();
        if(ipPort<0){
            throw new IllegalArgumentException("An IP port must be greater or equal to 0!");
        }
        sessionProperties.put(SESSION_HOST_PORT,Integer.toString(ipPort));
    }
    public void setCodePage(final String charEncoding){
        failIfConnected();
        if(isSignificant(charEncoding)){
            if(charEncoding.startsWith("Cp")){
                sessionProperties.put(SESSION_CODE_PAGE,charEncoding.substring(2));
            }else {
                sessionProperties.put(SESSION_CODE_PAGE,charEncoding);
            }
        }
    }

    public void setDeviceName(String deviceName){
        failIfConnected();
        if(isSignificant(deviceName)){
            sessionProperties.put(SESSION_DEVICE_NAME,deviceName);
        }
    }

    public void setScreenSize(String screenSize){
        failIfConnected();
        if("27x132".equals(screenSize)){
            sessionProperties.put(SESSION_SCREEN_SIZE,SCREEN_SIZE_27X132_STR);
        }else {
            sessionProperties.put(SESSION_SCREEN_SIZE,SCREEN_SIZE_24X80_STR);
        }
    }
    public void setSignOnEmbedded(boolean embed){
        failIfConnected();
        this.embeddedSignOn=embed;
    }
    public void setSignOnUser(String user){
        failIfNot10(user);
        this.user=user;
    }

    public void setSignOnPasswors(String password){
        failIfNot10(password);
        this.password=password;
    }
    public void setSignOnLibrary(String library){
        failIfConnected();
        failIfNot10(library);
        this.library=library;
    }
    public void setSignOnMenu(String menu){
        failIfConnected();
        failIfNot10(menu);
        this.menu=menu;
    }
    public void setSignOnProgram(String program){
        failIfConnected();
        failIfNot10(program);
        this.program=program;
    }

    public void setAfterSignOnMacro(String macro){
        failIfConnected();
        this.afterSignOn=macro;
    }
    public void setInitialCommand(String command){
        failIfConnected();
        this.initialCommand=command;
    }
    public void setVisibilityInterval(int interval){
        failIfConnected();
        this.visibilityInterval=interval;
    }

    @Override
    public void connect(){
        failIfConnected();
        if(!isSignificant(this.user)){
            connectSimple();
        }else {
            if(this.embeddedSignOn){
                connectEmbedded();
            }
            Runnable runnable = new Runnable() {
                int tryConnection;

                @Override
                public void run() {
                   if(tryConnection++ <50 && !isConnected()){
                       try {
                           Thread.sleep(100);
                       }catch (InterruptedException ex){
                           Thread.currentThread().interrupt();
                       }
                       SwingUtilities.invokeLater(this);
                   }else{
                       doAfterSingnOn();
                       doInitialCommand();
                       doVisibility();
                   }
                }
            };
            runnable.run();
        }

    }

    public void signOff(){
        if(Objects.nonNull(session.getVT()) && this.isConnected()){
            this.session.getVT().systemRequest("90");
        }
    }

    public void systemRequest(String srCode){
     this.session.getVT().systemRequest(srCode);
    }

    @Override
    public void doLayout(){
        super.doLayout();
        Rectangle prevRect = this.getBounds();
    }


    @Override
    public Dimension getPreferredSize(){
        if(preferredSize== null){
            this.setPreferredSize(SessionBean.deriveOptimalSize(this,this.getFont(),80,24));
        }
        return super.getPreferredSize();
    }

    @Override
    public void setPreferredSize(Dimension preferredSize){
        this.preferredSize=preferredSize;
        this.setPreferredSize(preferredSize);
    }
    //========================
    //private metohods field
    //========================
    private void connectSimple(){
        super.connect();
    }

    private void connectEmbedded(){
        if(isSignificant(user)){
            sessionProperties.put("SESSION_CONNECT_USER",user);
        }
        if(Objects.nonNull(password)){
            sessionProperties.put("SESSION_CONNECT_PASSWORD",password);
        }
        if(isSignificant(program)){
            sessionProperties.put("SESSION_CONNECT_PROGRAM",program);
        }
        if(isSignificant(menu)){
            sessionProperties.put("SESSION_CONNECT_MENU",menu);
        }
        if(isSignificant(library)){
            sessionProperties.put("SESSION_CONNECT_LIBRARY",library);
        }
       super.connect();
    }

    public void connectSimulated(){
    StringBuilder sb = new StringBuilder();

    if(isSignificant(user)) {
        sb.append(user);
    }
    if(!isFieldLenght(user)) {
        sb.append(KeyMnemonic.TAB.mnemonic);
    }

    if(isSignificant(password)) {
        sb.append(password);
    }

    if(!isFieldLenght(password)) {
        sb.append(KeyMnemonic.TAB.mnemonic);
    }
     if(isSignificant(program) || isSignificant(menu) || isSignificant(library)){
         if(isSignificant(program)){
             sb.append(program);
         }
         if(!isFieldLenght(program)){
             sb.append(KeyMnemonic.TAB.mnemonic);
         }

         if(isSignificant(menu)){
             sb.append(menu);
         }
         if(!isFieldLenght(menu)){
             sb.append(KeyMnemonic.TAB.mnemonic);
         }
         if(isSignificant(library)){
             sb.append(library);
         }
     }
     sb.append(KeyMnemonic.ENTER.mnemonic);
     this.getScreen().sendKeys(sb.toString());
    }

    private  void doAfterSingnOn(){
        if(isSignificant(afterSignOn)){
            this.getScreen().sendKeys(afterSignOn);
        }
    }

    private void doInitialCommand(){
        if(isSignificant(initialCommand)){
            this.getScreen().sendKeys(initialCommand + KeyMnemonic.ENTER.mnemonic);
        }
    }

    private void doVisibility(){
        if(!isVisible() && visibilityInterval>0){
            Timer t = new Timer(visibilityInterval,new DoVisible());
            t.setRepeats(false);
            t.start();
        } else if (!isVisible()) {
            new DoVisible().run();
        }
    }

    private boolean isFieldLenght(String param){
        return Objects.nonNull(param) && param.length()==10;
    }

    private void failIfConnected(){
        if(Objects.nonNull(session.getVT()) && isConnected()){
            throw new IllegalStateException("Cannot change property after being failIfConnected !");
        }
    }

  private void failIfNot10(String param){
        if(Objects.nonNull(param) && param.length()>13){
            throw  new IllegalArgumentException("The length of the parameter cannot exceed 10 positions");
        }
  }

    //================================
    //UtilityMethods
    //================================
    private static boolean isSignificant(String param){
        if(Objects.nonNull(param) && !param.isEmpty()){
            return true;
        }
        return false;
    }

    private static Dimension deriveOptimalSize(JComponent comp, Font f,int nrChars,int nrLines){
        return deriveOptimalSize(comp,f,comp.getBorder(),nrChars,nrLines);
    }

    private static Dimension deriveOptimalSize(JComponent comp, Font f, Border border,int nrChars,int nrLines){
        if(comp==null){
            return null;
        }
        FontMetrics fm=null;
        Graphics g = comp.getGraphics();
        if(Objects.nonNull(g)){
            fm=g.getFontMetrics(f);
        }else{
            fm= comp.getFontMetrics(f);
        }

        Insets insets=border==null ? new Insets(0,0,0,0): border.getBorderInsets(comp);
        int height = fm.getHeight() * nrLines + insets.top + insets.bottom;
        int width = nrChars * fm.charWidth('M') + insets.left + insets.right;

        return new Dimension(width + 2,height);

    }

    public void setNoSaveConfigFile(){
        this.sesConfig.removeProperty("saveme");
    }

    private class DoVisible implements ActionListener,Runnable{

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(this);
        }

        @Override
        public void run() {
            SessionBean.this.setVisible(true);
            SessionBean.this.requestFocusInWindow();
        }
    }

}
