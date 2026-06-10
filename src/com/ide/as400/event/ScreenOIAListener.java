/**
 * Clase: ScreenOIAListener.java
 * Descripción: Interfaz de escucha para cambios en el área OIA (Operator Information Area) de la pantalla, notificando eventos como bloqueo de teclado, modo insertar, luz de mensajes y estado del cursor.
 */

package com.ide.as400.event;

import com.ide.as400.framework.tn5250.ScreenOIA;

public interface ScreenOIAListener {

  public static final int OIA_CHANGED_INSERT_MODE = 0;
  public static final int OIA_CHANGED_KEYS_BUFFERED = 1;
  public static final int OIA_CHANGED_KEYBOARD_LOCKED = 2;
  public static final int OIA_CHANGED_MESSAGELIGHT = 3;
  public static final int OIA_CHANGED_SCRIPT = 4;
  public static final int OIA_CHANGED_BELL = 5;
  public static final int OIA_CHANGED_CLEAR_SCREEN = 6;
  public static final int OIA_CHANGED_INPUTINHIBITED = 7;
  public static final int OIA_CHANGED_CURSOR = 8;


   public void onOIAChanged(ScreenOIA oia, int change);

}
