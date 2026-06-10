/**
 * Clase: QuickEmailAction.java
 * Descripción: Acción del emulador que abre el diálogo de envío rápido de correo electrónico, asignada por defecto al atajo Alt+F.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;
import com.ide.as400.mailtools.SendEMailDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.QUICK_MAIL;
import com.ide.as400.SessionPanel;

/**
 * Quick Email Action
 */
public class QuickEmailAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public QuickEmailAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        QUICK_MAIL.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    Runnable emailIt = new Runnable() {
      public void run() {
        new SendEMailDialog((JFrame) SwingUtilities.getRoot(session),
            session, false);
      }

    };
    SwingUtilities.invokeLater(emailIt);
  }
}
