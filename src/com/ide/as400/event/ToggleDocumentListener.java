/**
 * Clase: ToggleDocumentListener.java
 * Descripción: Interfaz de escucha que notifica cuando un documento alterna entre estado vacío y no vacío, utilizada para habilitar o deshabilitar controles relacionados con el contenido.
 */
package com.ide.as400.event;

public interface ToggleDocumentListener {

   public void toggleNotEmpty();
   public void toggleEmpty();

}
