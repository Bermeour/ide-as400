/**
 * Clase: GUIViewInterface.java
 * Descripción: Clase abstracta base para todas las interfaces gráficas principales del emulador, define los métodos necesarios para agregar, eliminar y navegar entre las vistas de sesión.
 */
package com.ide.as400.interfaces;

import com.ide.as400.My5250;
import com.ide.as400.event.SessionChangeEvent;
import com.ide.as400.event.SessionJumpEvent;
import com.ide.as400.gui.GenericTn5250JFrame;
import com.ide.as400.SessionPanel;

/**
 * Abstract class for all main GUI interfaces.<br>
 * Direct known subclasses:
 */
public abstract class GUIViewInterface extends GenericTn5250JFrame {

   private static final long serialVersionUID = 1L;
   protected static My5250 me;
   protected static int sequence;
   protected int frameSeq;

   public GUIViewInterface(My5250 m) {
      super();
      me = m;
   }

   public int getFrameSequence() {
      return frameSeq;
   }

   public abstract void addSessionView(String descText, SessionPanel session);
   public abstract void removeSessionView(SessionPanel targetSession);
   public abstract boolean containsSession(SessionPanel session);
   public abstract int getSessionViewCount();
   public abstract SessionPanel getSessionAt(int index);
   public abstract void onSessionJump(SessionJumpEvent jumpEvent);
   public abstract void onSessionChanged(SessionChangeEvent changeEvent);

}
