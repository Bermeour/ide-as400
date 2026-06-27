/**
 * Clase: SessionJumpEvent.java
 * Descripción: Evento que representa un salto de navegación entre sesiones del emulador, indicando la dirección del salto (siguiente o anterior).
 */
package com.ide.as400.event;

import java.util.EventObject;

public class SessionJumpEvent extends EventObject {

  private static final long serialVersionUID = 1L;

  private String message;
  private int jumpDirection;

  public SessionJumpEvent(Object obj) {
    super(obj);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getJumpDirection() {
    return jumpDirection;
  }

  public void setJumpDirection(int direction) {
    this.jumpDirection = direction;
  }

}
