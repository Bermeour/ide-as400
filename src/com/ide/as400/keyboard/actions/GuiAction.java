/**
 * Clase: GuiAction.java
 * Descripción: Acción del emulador que activa o desactiva la interfaz gráfica de la pantalla, asignada por defecto al atajo Alt+G.
 */
package com.ide.as400.keyboard.actions;


import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.GUI;
import com.ide.as400.SessionPanel;

/**
 * Toggle gui
 */
public class GuiAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public GuiAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        GUI.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.getScreen().toggleGUIInterface();
  }
}
