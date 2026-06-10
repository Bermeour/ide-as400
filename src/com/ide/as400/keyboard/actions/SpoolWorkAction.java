/**
 * Clase: SpoolWorkAction.java
 * Descripción: Acción del emulador que invoca la funcionalidad de trabajo con archivos spool del sistema AS/400, asignada al atajo Alt+W.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.SPOOL_FILE;
import com.ide.as400.SessionPanel;

/**
 * Work with spooled file
 */
public class SpoolWorkAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public SpoolWorkAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        SPOOL_FILE.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.actionSpool();
  }
}
