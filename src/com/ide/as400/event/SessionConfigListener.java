package com.ide.as400.event;

/**
 * Clase: SessionConfigListener.java
 * Descripción: Interfaz de escucha para recibir notificaciones cuando la configuración de una sesión del emulador ha sido modificada.
 */
public interface SessionConfigListener {

  /**
   * Update the configuration settings
   *
   * @param sessionConfigEvent sessionConfigEvent
   */
  public void onConfigChanged(SessionConfigEvent sessionConfigEvent);
}
