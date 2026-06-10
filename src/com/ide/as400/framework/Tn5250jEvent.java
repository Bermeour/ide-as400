/**
 * Clase: Tn5250jEvent.java
 * Descripción: Representa un evento generado en una sesión TN5250j, encapsulando el estado de la
 * pantalla 5250 en el momento del evento, incluyendo los caracteres visibles y los campos de entrada.
 */
package com.ide.as400.framework;

import com.ide.as400.framework.tn5250.Screen5250;
import com.ide.as400.framework.tn5250.ScreenFields;

public class Tn5250jEvent {

  private Screen5250 screen;
  private char[] data;
  private ScreenFields fields;

  public Tn5250jEvent() {
    screen = null;
  }

  public Tn5250jEvent(Screen5250 newscreen) {
    screen = newscreen;
    // changed by Kenneth - This should be replaced with a call to
    //   getPlane method of screen object when they are implemented.  These
    //   new methods will also do the array copy.
    char[] original = screen.getCharacters();
    data = new char[original.length];
    System.arraycopy(original, 0, data, 0, original.length);
    this.fields = newscreen.getScreenFields();
  }

  public char[] getData() {
    return data;
  }

  public Screen5250 getScreen() {
    return screen;
  }

  public ScreenFields getFields() {
    return this.fields;
  }
}
