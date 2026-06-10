package com.ide.as400.interfaces;

/**
 * Clase: ScanListener.java
 * Descripción: Interfaz de escucha para el análisis sintáctico de comandos, notificando cuando se ha escaneado un comando junto con el resto del texto sin procesar.
 */
public interface ScanListener
{
  public void scanned(String command, String remainder);
}
