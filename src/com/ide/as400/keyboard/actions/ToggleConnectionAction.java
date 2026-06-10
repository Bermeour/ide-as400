/**
 * Clase: ToggleConnectionAction.java
 * Descripción: Acción del emulador que alterna el estado de la conexión de la sesión entre conectado y desconectado, asignada al atajo Alt+X.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.TOGGLE_CONNECTION;
import com.ide.as400.SessionPanel;

/**
 * Toggle connection from/to connected
 */
public class ToggleConnectionAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public ToggleConnectionAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        TOGGLE_CONNECTION.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK),
        keyMap);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    session.toggleConnection();
  }
}
