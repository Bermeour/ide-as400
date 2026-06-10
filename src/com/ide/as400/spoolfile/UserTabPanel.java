package com.ide.as400.spoolfile;

/**
 * Clase: UserTabPanel.java
 * Descripción: Panel de filtro por usuario en el explorador de spool que permite seleccionar
 * todos los usuarios o filtrar los archivos de spool pertenecientes a un usuario específico.
 */

import java.awt.event.*;
import javax.swing.*;

import com.ide.as400.tools.AlignLayout;
import com.ide.as400.event.ToggleDocumentListener;
import com.ide.as400.gui.ToggleDocument;

public class UserTabPanel extends JPanel implements QueueFilterInterface,
                                                         ToggleDocumentListener {

   private static final long serialVersionUID = 1L;
JRadioButton all;
   JRadioButton select;
   JTextField user;;

   public UserTabPanel() {
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

      all.setSelected(false);

      select = new JRadioButton("User");
      select.setSelected(true);
      select.addItemListener(new java.awt.event.ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            select_itemStateChanged(e);
         }
      });

      user = new JTextField("*CURRENT",15);
      ToggleDocument td = new ToggleDocument();
      td.addToggleDocumentListener(this);
      user.setDocument(td);
      user.setText("*CURRENT");

      ButtonGroup bg = new ButtonGroup();
      bg.add(all);
      bg.add(select);

      add(all);
      add(new JLabel(""));
      add(select);
      add(user);

      setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

   }

   /**
    * Reset to default value(s)
    */
   public void reset() {

//      user.setEnabled(true);
      user.setText("*CURRENT");
      select.setSelected(true);

   }

   void select_itemStateChanged(ItemEvent e) {
//      if (select.isSelected())
//         user.setEnabled(true);
//      else
//         user.setEnabled(false);
   }

   public void toggleNotEmpty() {

      select.setSelected(true);

   }

   public void toggleEmpty() {

   }

   public String getUser() {
      if (all.isSelected())
         return "*ALL";
      else
         return user.getText().trim();
   }

   public void setUser(String filter) {

      user.setText(filter);
   }
}
