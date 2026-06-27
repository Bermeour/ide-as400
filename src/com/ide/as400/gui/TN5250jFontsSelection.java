/**
 * Clase: TN5250jFontsSelection.java
 * Descripción: Componente JComboBox que se rellena automáticamente con todas las fuentes disponibles en el
 * entorno gráfico local, excluyendo fuentes con nombre que contengan puntos.
 */
package com.ide.as400.gui;

import java.awt.*;
import javax.swing.*;

public class TN5250jFontsSelection extends JComboBox {

   private static final long serialVersionUID = 1L;

public TN5250jFontsSelection() {
      super();
      // fonts
      Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

      for (int x = 0; x < fonts.length; x++) {
         if (fonts[x].getFontName().indexOf('.') < 0)
            addItem(fonts[x].getFontName());
      }

   }
}
