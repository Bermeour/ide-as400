/**
 * Clase: SendScreenImageToFile.java
 * Descripción: Permite al usuario guardar la pantalla actual de la sesión 5250 como imagen PNG
 * en un archivo local seleccionado mediante un diálogo de guardar.
 */
package com.ide.as400.tools;

import java.io.*;
import javax.swing.*;
import java.awt.Frame;

import com.ide.as400.tools.logging.*;
import com.ide.as400.tools.encoder.EncodeComponent;
import com.ide.as400.tools.filters.XTFRFileFilter;
import com.ide.as400.gui.TN5250jFileChooser;
import com.ide.as400.SessionPanel;

public class SendScreenImageToFile {

   SessionPanel session;
   //  Change sent by Luc - LDC to pass a parent frame like the other dialogs
   Frame  parent;
   private TN5250jLogger  log = TN5250jLogFactory.getLogger (this.getClass());

   public SendScreenImageToFile(Frame parent, SessionPanel ses) {

      session = ses;
      this.parent = parent;


      try {
         jbInit();
      }
      catch(Exception ex) {
         log.warn("Error in constructor: "+ ex.getMessage());
      }
   }

   void jbInit() throws Exception {
      getPCFile();

   }

   /**
    * Get the local file from a file chooser
    */
   private void getPCFile() {

      String workingDir = System.getProperty("user.dir");
      TN5250jFileChooser pcFileChooser = new TN5250jFileChooser(workingDir);

      XTFRFileFilter pngFilter = new XTFRFileFilter("png", "Portable Network Graphics");

      pcFileChooser.setFileFilter(pngFilter);

      int ret = pcFileChooser.showSaveDialog(parent);

      // check to see if something was actually chosen
      if (ret == JFileChooser.APPROVE_OPTION) {

         File file;

         try {
            if (!pcFileChooser.getSelectedFile().getCanonicalPath().endsWith(".png"))
               file = new File(pcFileChooser.getSelectedFile().getCanonicalPath()
                                 + ".png");
            else
               file = pcFileChooser.getSelectedFile();


            EncodeComponent.encode(EncodeComponent.PNG,session, file);
         }
         catch (Exception e) {
            log.warn("Error generating PNG exception caught: " + e.getMessage());

         }

      }

   }

}
