/**
 * Clase: EmulatorActionListener.java
 * Descripción: Interfaz de escucha para recibir notificaciones sobre acciones solicitadas al emulador, tales como apertura o cierre de sesiones.
 */
package com.ide.as400.event;

public interface EmulatorActionListener {

   public void onEmulatorAction(EmulatorActionEvent actionEvent);
}
