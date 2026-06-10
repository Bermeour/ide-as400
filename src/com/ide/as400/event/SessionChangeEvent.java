package com.ide.as400.event;

import java.util.EventObject;

/**
 * Clase: SessionChangeEvent.java
 * Descripción: Evento que notifica un cambio de estado en una sesión del emulador, transportando un mensaje descriptivo y un código de estado numérico.
 */
public class SessionChangeEvent extends EventObject {

   private static final long serialVersionUID = 1L;

   public SessionChangeEvent(Object obj){
      super(obj);

   }

   public SessionChangeEvent(Object obj, String s) {
      super(obj);
      message = s;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String s) {
      message = s;
   }

   public int getState() {

      return state;
   }

   public void setState(int s) {

      state = s;
   }

   private String message;
   private int state;
}
