/**
 * Clase: TransferAction.java
 * Descripción: Acción del emulador que abre el diálogo de transferencia de archivos entre el sistema local y el AS/400, asignada al atajo Alt+T.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;
import com.ide.as400.tools.XTFRFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.FILE_TRANSFER;
import com.ide.as400.SessionPanel;

/**
 * Display session attributes
 */
public class TransferAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public TransferAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        FILE_TRANSFER.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    new XTFRFile((Frame) SwingUtilities.getRoot(session),
        session.getVT(), session);
  }
}
