/**
 * Clase: RulerAction.java
 * Descripción: Acción del emulador que activa o desactiva el cursor en forma de cruz (crosshair) como regla visual en la pantalla, asignada al atajo Alt+L.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.CURSOR;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class RulerAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public RulerAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        CURSOR.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.crossHair();
  }
}
