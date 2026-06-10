/**
 * Clase: OpenSameAction.java
 * Descripción: Acción del emulador que abre una sesión duplicada con la misma configuración de la sesión actual, asignada al atajo Alt+U.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.OPEN_SAME;
import com.ide.as400.SessionPanel;

/**
 * Open Same Session emulator action to open a duplicate session
 */
public class OpenSameAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public OpenSameAction(SessionPanel session, KeyMapper keyMap) {
    super(session, OPEN_SAME.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.startDuplicateSession();
  }
}
