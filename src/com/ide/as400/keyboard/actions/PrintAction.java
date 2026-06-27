/**
 * Clase: PrintAction.java
 * Descripción: Acción del emulador que imprime el contenido actual de la pantalla de la sesión, asignada por defecto al atajo Alt+P.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.PRINT_SCREEN;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class PrintAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public PrintAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        PRINT_SCREEN.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_MASK),
        keyMap);

  }

  public void actionPerformed(ActionEvent e) {
    session.printMe();
  }
}
