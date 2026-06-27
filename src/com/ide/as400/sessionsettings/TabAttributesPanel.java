package com.ide.as400.sessionsettings;

/**
 * Clase: TabAttributesPanel.java
 * Descripción: Panel de configuración de las opciones de pestañas de sesión,
 * que permite habilitar o deshabilitar la confirmación al cerrar una pestaña de sesión activa.
 */

import com.ide.as400.tools.LangTool;

import javax.swing.*;
import java.awt.*;
import com.ide.as400.SessionConfig;

class TabAttributesPanel extends AttributesPanel {

  private static final long serialVersionUID = 1L;
  private JCheckBox tabCloseCheck;

  TabAttributesPanel(SessionConfig config) {
    super(config, "Tabs");
  }

  // Component initialization
  public void initPanel() throws Exception {

    setLayout(new BorderLayout());
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    add(contentPane, BorderLayout.NORTH);

    // Define close tab confirmation panel
    JPanel tabConfirm = new JPanel();
    tabConfirm.setBorder(BorderFactory.createTitledBorder(LangTool.getString("sa.titleTabOptions")));

    tabCloseCheck = new JCheckBox(LangTool.getString("sa.confirmTabClose"));

    // Check if tab close confirmation is to be checked
    tabCloseCheck.setSelected(getStringProperty("confirmTabClose").equals("Yes"));

    tabConfirm.add(tabCloseCheck);

    contentPane.add(tabConfirm);

  }

  public void applyAttributes() {

    String value = "";

    if (tabCloseCheck.isSelected()) {
      value = "Yes";
    } else {
      value = "No";
    }

    changes.firePropertyChange(this, "confirmTabClose", getStringProperty("confirmTabClose"), value);

    setProperty("confirmTabClose", value);

  }

}
