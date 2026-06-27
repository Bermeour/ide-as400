package com.ide.as400.event;

/**
 * Clase: SessionListener.java
 * Descripción: Interfaz de escucha para recibir notificaciones sobre cambios de estado en una sesión del emulador AS/400.
 */
public interface SessionListener {

   public void onSessionChanged(SessionChangeEvent changeEvent);
}
