/**
 * Clase: TN5250jSecurityAccessDialog.java
 * Descripción: Utilitario estático que muestra un diálogo de error cuando ocurre una excepción de seguridad,
 * presentando el mensaje de la excepción en un cuadro de diálogo de error.
 */
package com.ide.as400.gui;

import javax.swing.JOptionPane;

import com.ide.as400.tools.LangTool;
import com.ide.as400.gui.GenericTn5250JFrame;

public class TN5250jSecurityAccessDialog {

   // set so outsiders can not initialize the dialog.
   private TN5250jSecurityAccessDialog() {

   }

   static public void showErrorMessage(SecurityException se) {

      GenericTn5250JFrame parent = new GenericTn5250JFrame();
      JOptionPane.showMessageDialog(parent,LangTool.getString("messages.SADMessage")
                                    + se.getMessage()
                                    ,LangTool.getString("messages.SADTitle"),
                                    JOptionPane.ERROR_MESSAGE);


   }
}
