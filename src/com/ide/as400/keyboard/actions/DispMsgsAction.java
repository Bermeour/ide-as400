/**
 * Clase: DispMsgsAction.java
 * Descripción: Acción del emulador que envía una petición al sistema para mostrar los mensajes del sistema (SysReq '4'), asignada al atajo Alt+M.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.DISP_MESSAGES;
import com.ide.as400.SessionPanel;

/**
 * Display system messages
 */
public class DispMsgsAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public DispMsgsAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        DISP_MESSAGES.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.getVT().systemRequest('4');
  }
}
