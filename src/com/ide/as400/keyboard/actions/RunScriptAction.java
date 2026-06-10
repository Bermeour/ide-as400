/**
 * Clase: RunScriptAction.java
 * Descripción: Acción del emulador que abre el diálogo para ejecutar un script o macro sobre la sesión activa, asignada al atajo Alt+R.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;
import com.ide.as400.tools.Macronizer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.RUN_SCRIPT;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class RunScriptAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public RunScriptAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        RUN_SCRIPT.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.ALT_MASK),
        keyMap);

  }

  public void actionPerformed(ActionEvent e) {
    Macronizer.showRunScriptDialog(session);
    session.getFocusForMe();
  }
}
