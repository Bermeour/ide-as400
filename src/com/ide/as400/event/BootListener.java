package com.ide.as400.event;

import java.util.EventListener;

/**
 * Clase: BootListener.java
 * Descripción: Interfaz de escucha para recibir eventos de arranque del emulador, notificando cuando se han recibido opciones de inicialización de sesión.
 */
public interface BootListener extends EventListener {

    public abstract void bootOptionsReceived(BootEvent bootevent);

}
