/**
 * Clase: PasteAction.java
 * Descripción: Acción del emulador que pega texto desde el portapapeles del sistema en la posición actual del cursor de la pantalla, asignada al atajo Alt+V.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;
import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static com.ide.as400.keyboard.KeyMnemonic.PASTE;
import com.ide.as400.SessionPanel;

/**
 * Paste from the clipboard
 */
public class PasteAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  private final TN5250jLogger log = TN5250jLogFactory.getLogger(this.getClass());

  public PasteAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        PASTE.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent event) {
    try {
      Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
      final Transferable transferable = cb.getContents(this);
      if (transferable != null) {
        final String content = (String) transferable.getTransferData(DataFlavor.stringFlavor);
        session.getScreen().pasteText(content, false);
      }
    } catch (HeadlessException e1) {
      log.debug("HeadlessException", e1);
    } catch (UnsupportedFlavorException e1) {
      log.debug("the requested data flavor is not supported", e1);
    } catch (IOException e1) {
      log.debug("data is no longer available in the requested flavor", e1);
    }
  }

}
