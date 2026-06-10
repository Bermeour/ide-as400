/**
 * Clase: NewSessionAction.java
 * Descripción: Acción del emulador que inicia una nueva sesión de emulación, asignada por defecto al atajo Alt+N.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.OPEN_NEW;
import com.ide.as400.SessionPanel;

/**
 * New Session emulator action to open new sessions
 */
public class NewSessionAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public NewSessionAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        OPEN_NEW.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.startNewSession();
  }
}
