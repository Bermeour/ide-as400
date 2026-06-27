package com.ide.as400.spoolfile;

/**
 * Clase: UserDataTabPanel.java
 * Descripción: Panel de filtro por datos de usuario (user data) en el explorador de spool,
 * que permite mostrar todos los archivos de spool o filtrar por un valor de datos de usuario específico.
 */

import java.awt.event.*;
import javax.swing.*;

import com.ide.as400.tools.AlignLayout;
import com.ide.as400.event.ToggleDocumentListener;
import com.ide.as400.gui.ToggleDocument;

public class UserDataTabPanel extends JPanel implements QueueFilterInterface,
                                                         ToggleDocumentListener {

   private static final long serialVersionUID = 1L;
JRadioButton all;
   JRadioButton select;
   JTextField userData;

   public UserDataTabPanel() {
      try {
         jbInit();
      }
      catch(Exception ex) {
         ex.printStackTrace();
      }
   }

   void jbInit() throws Exception {

      setLayout(new AlignLayout(2,5,5));

      all = new JRadioButton("All");

      all.setSelected(true);

      select = new JRadioButton("User Data");
      select.setSelected(false);
      select.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            select_itemStateChanged(e);
         }
      });

      userData = new JTextField(15);
//      userData.setEnabled(false);
      ToggleDocument td = new ToggleDocument();
      td.addToggleDocumentListener(this);
      userData.setDocument(td);

      ButtonGroup bg = new ButtonGroup();
      bg.add(all);
      bg.add(select);

      add(all);
      add(new JLabel(""));
      add(select);
      add(userData);

      setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

   }

   /**
    * Reset to default value(s)
    */
   public void reset() {

//      userData.setEnabled(false);
      userData.setText("");
      all.setSelected(true);

   }

   void select_itemStateChanged(ItemEvent e) {
//      if (select.isSelected())
//         userData.setEnabled(true);
//      else
//         userData.setEnabled(false);
   }

   public void toggleNotEmpty() {

      select.setSelected(true);

   }

   public void toggleEmpty() {

   }

   public String getUserData() {
      if (all.isSelected())
         return "";
      else
         return userData.getText().trim();
   }

   public void setUserData(String filter) {

      userData.setText(filter);
   }
}
