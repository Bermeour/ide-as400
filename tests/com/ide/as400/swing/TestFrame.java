package com.ide.as400.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ide.as400.SessionBean;
import com.ide.as400.interfaces.ConfigureFactory;

/**
 * Clase: TestFrame.java
 * Descripción: Marco Swing de prueba que conecta un SessionBean a un servidor AS/400 de desarrollo
 * y muestra el terminal TN5250 dentro de un JFrame para verificación visual de la interfaz.
 */
public class TestFrame extends JFrame {

	private static final long serialVersionUID = 1L;

  static {
    ConfigureFactory.getInstance();
    // WVL - LDC : 11/07/2003
    //
    //       LEAVE THIS INITIALIZER IN THIS PLACE SO IT HAPPENS
    //       BEFORE ANY OTHER STATIC INITIALISATION
    com.ide.as400.tools.LangTool.init();
  }

  public static void main(String[] args)
  {
    JFrame frm = new TestFrame();
    frm.pack();
    frm.setVisible(true);
  }

  public TestFrame()
  {
    super("Terminal");
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    try
    {
      String system = "LDCDEV";
      String config = system + ".properties";

      session = new SessionBean(config, system);
      session.setHostName("193.168.51.1");
      session.setCodePage("Cp1141");
      session.connect();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.exit(-1);
    }

    terminal = new JTerminal(session.getSession());

//    RepaintManager repaintManager = RepaintManager.currentManager(terminal);
//    repaintManager.setDoubleBufferingEnabled(false);
//    terminal.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);


    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(terminal);
  }

  private SessionBean session;
  private JTerminal terminal;
}
