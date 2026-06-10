/**
 * Clase: KeypadAttributesPanelLearningTest.java
 * Descripción: Prueba visual (manual) que instancia y muestra el panel KeypadAttributesPanel
 * dentro de un JFrame para verificar su apariencia y comportamiento con una configuración de sesión de prueba.
 */
package com.ide.as400.sessionsettings;

import com.ide.as400.SessionConfig;
import com.ide.as400.tools.LangTool;

import javax.swing.*;
import java.awt.*;

public class KeypadAttributesPanelLearningTest {

  public static void main(String[] args) {
    LangTool.init();

    SessionConfig config = new SessionConfig("test-configuration-resource", "test-session");
    KeypadAttributesPanel keypadAttributesPanel = new KeypadAttributesPanel(config);

    showPanel(keypadAttributesPanel);
  }

  private static void showPanel(JPanel panel) {
    JFrame frame = new JFrame("FrameDemo");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }
}