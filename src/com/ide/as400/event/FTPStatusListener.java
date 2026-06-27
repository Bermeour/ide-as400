package com.ide.as400.event;

import java.util.EventListener;

/**
 * Clase: FTPStatusListener.java
 * Descripción: Interfaz de escucha para recibir notificaciones sobre el estado de operaciones FTP, con métodos para el estado general, el estado de comandos y la información de archivos.
 */
public interface FTPStatusListener extends EventListener {

    public abstract void statusReceived(FTPStatusEvent statusevent);
    public abstract void commandStatusReceived(FTPStatusEvent statusevent);
    public abstract void fileInfoReceived(FTPStatusEvent statusevent);

}
