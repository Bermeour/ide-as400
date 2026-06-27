package com.ide.as400.event;

import java.util.EventObject;

/**
 * Clase: BootEvent.java
 * Descripción: Evento de arranque que transporta opciones de nueva sesión y mensajes asociados al proceso de inicialización del emulador.
 */
public class BootEvent extends EventObject {

   private static final long serialVersionUID = 1L;
public BootEvent(Object obj){
      super(obj);

   }

   public BootEvent(Object obj, String s) {
      super(obj);
      bootOptions = s;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String s) {
      message = s;
   }

   public String getNewSessionOptions() {

      return bootOptions;
   }

   public void setNewSessionOptions(String s) {

      bootOptions = s;
   }

   private String message;
   private String bootOptions;
}
