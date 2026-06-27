/**
 * Clase: CloseAction.java
 * Descripción: Acción del emulador que solicita confirmación al usuario para cerrar la sesión activa, asignada por defecto al atajo Alt+Q.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.CLOSE;
import com.ide.as400.SessionPanel;


/**
 * Display session attributes
 */
public class CloseAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public CloseAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        CLOSE.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK),
        keyMap);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    session.confirmCloseSession(true);
  }
}
