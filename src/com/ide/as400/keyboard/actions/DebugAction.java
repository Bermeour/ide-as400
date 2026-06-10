/**
 * Clase: DebugAction.java
 * Descripción: Acción del emulador que activa o desactiva el modo de depuración de la sesión, asignada por defecto al atajo Alt+O.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.DEBUG;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class DebugAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public DebugAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        DEBUG.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_MASK),
        keyMap);

  }

  public void actionPerformed(ActionEvent e) {
    session.toggleDebug();
  }
}
