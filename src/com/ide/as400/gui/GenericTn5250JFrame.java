/**
 * Clase: GenericTn5250JFrame.java
 * Descripción: Clase base para todas las ventanas de la aplicación que establece el ícono estándar y
 * proporciona un método utilitario para centrar el frame en pantalla.
 */
package com.ide.as400.gui;

import java.awt.*;

import javax.swing.JFrame;

import com.ide.as400.tools.GUIGraphicsUtils;

public class GenericTn5250JFrame extends JFrame {

	private static final long serialVersionUID = 7349671770294342782L;

	protected boolean packFrame = false;

   public GenericTn5250JFrame() {
      super();
      java.util.List<Image> icons = GUIGraphicsUtils.getApplicationIcons();
      setIconImages(icons);
      new AppleApplicationTools().tryToSetDockIconImages(icons);
   }

   public void centerFrame() {

      if (packFrame)
         pack();
      else
         validate();

      //Center the window
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = getSize();
      if (frameSize.height > screenSize.height)
         frameSize.height = screenSize.height;
      if (frameSize.width > screenSize.width)
         frameSize.width = screenSize.width;

      setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);


   }

}
