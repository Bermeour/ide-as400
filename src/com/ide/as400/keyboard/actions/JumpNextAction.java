/**
 * Clase: JumpNextAction.java
 * Descripción: Acción del emulador que salta a la siguiente sesión abierta, asignada por defecto al atajo Alt+AvPág.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.JUMP_NEXT;
import com.ide.as400.SessionPanel;

/**
 * Jump to the next session action
 */
public class JumpNextAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public JumpNextAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        JUMP_NEXT.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.nextSession();
  }
}
