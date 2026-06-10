package com.ide.as400.event;

/**
 * Clase: KeyChangeListener.java
 * Descripción: Interfaz de escucha que notifica cuando el mapa de teclas del emulador ha sido modificado, permitiendo a los componentes interesados recargar sus atajos de teclado.
 */
public interface KeyChangeListener {

   public void onKeyChanged();
}
