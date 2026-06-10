/**
 * Clase: SessionScroller.java
 * Descripción: Componente que habilita el desplazamiento de la pantalla 5250 mediante la rueda del ratón, enviando comandos de página arriba o página abajo a la sesión según la dirección del giro.
 */
package com.ide.as400;

import com.ide.as400.framework.tn5250.Screen5250;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static com.ide.as400.keyboard.KeyMnemonic.PAGE_DOWN;
import static com.ide.as400.keyboard.KeyMnemonic.PAGE_UP;

/**
 * Session Scroller to allow the use of the mouse wheel to move the list on the
 * screen up and down.
 */
public class SessionScroller implements MouseWheelListener {

  private Screen5250 screen = null;

  public void addMouseWheelListener(SessionPanel ses) {
    this.screen = ses.getScreen();
    ses.addMouseWheelListener(this);
  }

  public void removeMouseWheelListener(SessionPanel ses) {
    this.screen = null;
    ses.removeMouseWheelListener(this);
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    if (this.screen != null) {
      int notches = e.getWheelRotation();
      if (notches < 0) {
        screen.sendKeys(PAGE_UP);
      } else {
        screen.sendKeys(PAGE_DOWN);
      }
    }
  }

}
