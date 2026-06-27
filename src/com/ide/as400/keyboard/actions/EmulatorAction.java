/**
 * Clase: EmulatorAction.java
 * Descripción: Clase base abstracta para todas las acciones del emulador, gestiona el registro de atajos de teclado en los mapas de entrada y acción de la sesión.
 */
package com.ide.as400.keyboard.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

import com.ide.as400.keyboard.KeyMapper;
import com.ide.as400.interfaces.OptionAccessFactory;
import com.ide.as400.SessionPanel;

/**
 * Base class for all emulator actions
 */
public abstract class EmulatorAction extends AbstractAction {

   private static final long serialVersionUID = 1L;
// content pane to be used if needed by subclasses
   protected SessionPanel session;

   public EmulatorAction(SessionPanel session, String name) {

      super(name);
      this.session = session;
   }

   public EmulatorAction(SessionPanel session, String name, KeyStroke ks, KeyMapper keyMap) {

      this(session,name);

      setKeyStroke(name, ks, keyMap);
   }

   protected void setKeyStroke(String action, KeyStroke ks, KeyMapper keyMap) {

      if (OptionAccessFactory.getInstance().isRestrictedOption(action))
         return;

      if (KeyMapper.isKeyStrokeDefined(action)) {
         ks = KeyMapper.getKeyStroke(action);
      }

      session.getInputMap().put(ks,action);
      session.getActionMap().put(action, this );

      // check for alternate
      if (KeyMapper.isKeyStrokeDefined(action + ".alt2")) {
         ks = KeyMapper.getKeyStroke(action + ".alt2");
         session.getInputMap().put(ks,action);
         session.getActionMap().put(action,this );
      }

   }

   abstract public void actionPerformed(ActionEvent e);
}
