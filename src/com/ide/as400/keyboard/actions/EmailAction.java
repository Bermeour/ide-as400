/**
 * Clase: EmailAction.java
 * Descripción: Acción del emulador que envía por correo electrónico el contenido de la pantalla actual, asignada al atajo Alt+E.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.E_MAIL;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class EmailAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public EmailAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        E_MAIL.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.sendScreenEMail();
  }
}
