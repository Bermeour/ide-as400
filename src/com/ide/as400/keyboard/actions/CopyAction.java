/**
 * Clase: CopyAction.java
 * Descripción: Acción del emulador que copia el contenido seleccionado de la pantalla, asignada por defecto al atajo Alt+C.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.COPY;
import com.ide.as400.SessionPanel;

/**
 * Copy action
 */
public class CopyAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public CopyAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        COPY.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.actionCopy();
  }
}
