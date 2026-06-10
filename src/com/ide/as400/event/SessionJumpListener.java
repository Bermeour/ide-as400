package com.ide.as400.event;

/**
 * Clase: SessionJumpListener.java
 * Descripción: Interfaz de escucha que notifica cuando se solicita un salto de navegación entre sesiones del emulador.
 */
public interface SessionJumpListener {

   public void onSessionJump(SessionJumpEvent changeEvent);
}
