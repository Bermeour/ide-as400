/**
 * Clase: ScreenListener.java
 * Descripción: Interfaz de escucha que notifica cambios en el contenido de la pantalla del emulador, incluyendo actualizaciones de región y cambios en el tamaño de la pantalla.
 */

package com.ide.as400.event;

public interface ScreenListener {

   public void onScreenChanged(int inUpdate, int startRow, int startCol,
                                             int endRow, int endCol);

   public void onScreenSizeChanged(int rows, int cols);

}
