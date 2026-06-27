/**
 * Clase: TabClosedListener.java
 * Descripción: Interfaz de escucha que notifica cuando una pestaña de sesión del emulador debe cerrarse, recibiendo el índice de la pestaña a cerrar.
 */
package com.ide.as400.event;

public interface TabClosedListener {

   /**
    * Will be called, when a tab should be closed.
    *
    * @param tabToBeClosed number of the tab to be closed
    */
   public void onTabClosed(int tabToBeClosed);

}
