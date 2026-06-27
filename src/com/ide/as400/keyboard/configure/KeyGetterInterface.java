/**
 * Clase: KeyGetterInterface.java
 * Descripción: Clase abstracta base que extiende JLabel para capturar eventos de teclado, sirve como interfaz común para las implementaciones que recogen pulsaciones de teclas a asignar en el emulador.
 */
package com.ide.as400.keyboard.configure;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import javax.swing.JDialog;

import com.ide.as400.tools.system.OperatingSystem;

/**
 * This class is not really an interface but an class that extends label so that
 * we can display text as well as capture the key stroke(s) to assign to keys.
 *
 * The extending classes must override the key capture methods:
 *
 *    abstract private void processVTKeyPressed(KeyEvent e);
 *    abstract private void processVTKeyTyped(KeyEvent e);
 *    abstract private void processVTKeyReleased(KeyEvent e);
 *
 */
public abstract class KeyGetterInterface extends JLabel {

   private static final long serialVersionUID = 1L;
KeyEvent keyevent;
   boolean isAltGr;
   boolean isLinux;
   JDialog dialog;

   public KeyGetterInterface() {
      super();

      if (OperatingSystem.isUnix() && !OperatingSystem.isMacOS()) {
         isLinux = true;
      }

      addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                  processVTKeyTyped(e);

            }

            public void keyPressed(KeyEvent ke) {

               processVTKeyPressed(ke);
            }

            public void keyReleased(KeyEvent e) {

               processVTKeyReleased(e);

            }

      });

   }

   public void setDialog(JDialog dialog) {

      this.dialog = dialog;

   }

   public boolean isFocusTraversable () {
      return true;
   }

   /**
    * Override to inform focus manager that component is managing focus changes.
    * This is to capture the tab and shift+tab keys.
    */
   public boolean isManagingFocus() {
      return true;
   }

   abstract void processVTKeyPressed(KeyEvent e);

   abstract void processVTKeyTyped(KeyEvent e);

   abstract void processVTKeyReleased(KeyEvent e);
}
