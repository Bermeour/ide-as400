/**
 * Clase: HotspotsAction.java
 * Descripción: Acción del emulador que activa o desactiva los hotspots (zonas clicables) en la pantalla de la sesión, asignada al atajo Alt+S.
 */
package com.ide.as400.keyboard.actions;

import com.ide.as400.keyboard.KeyMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static com.ide.as400.keyboard.KeyMnemonic.HOTSPOTS;
import com.ide.as400.SessionPanel;

/**
 * Toggle Hot spots
 */
public class HotspotsAction extends EmulatorAction {

  private static final long serialVersionUID = 1L;

  public HotspotsAction(SessionPanel session, KeyMapper keyMap) {
    super(session,
        HOTSPOTS.mnemonic,
        KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK),
        keyMap);
  }

  public void actionPerformed(ActionEvent e) {
    session.toggleHotSpots();
  }
}
