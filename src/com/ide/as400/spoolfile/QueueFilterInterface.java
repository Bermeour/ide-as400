package com.ide.as400.spoolfile;

/**
 * Clase: QueueFilterInterface.java
 * Descripción: Interfaz que deben implementar los paneles de filtro de la cola de spool,
 * definiendo el contrato para restablecer los valores del filtro a sus valores predeterminados.
 */
public interface QueueFilterInterface {

   public abstract void reset();

}
