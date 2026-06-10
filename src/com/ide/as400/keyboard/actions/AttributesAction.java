/**
 * Clase: AttributesAction.java
 * Descripción: Acción del emulador que muestra los atributos de la sesión activa al ser invocada mediante el atajo de teclado Alt+D.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.DISP_ATTRIBUTES;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class AttributesAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public AttributesAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        DISP_ATTRIBUTES.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.actionAttributes();
  }
}
