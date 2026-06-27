/**
 * Clase: JumpPrevAction.java
 * Descripción: Acción del emulador que salta a la sesión anterior abierta, asignada por defecto al atajo Alt+RePág.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.JUMP_PREV;
import com.ide.as400.SessionPanel;

/**
 * Jump to the Previous session action
 */
public class JumpPrevAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public JumpPrevAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        JUMP_PREV.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.prevSession();
  }
}
