package com.ide.as400.sessionsettings;

/**
 * Clase: MouseAttributesPanel.java
 * Descripción: Panel de configuración del comportamiento del ratón en la sesión 5250,
 * que permite activar el doble clic como Enter y habilitar el uso de la rueda del ratón.
 */

import com.ide.as400.tools.LangTool;

import javax.swing.*;
import java.awt.*;
import com.ide.as400.SessionConfig;

class MouseAttributesPanel extends AttributesPanel {

  private static final long serialVersionUID = 1L;
  private JCheckBox dceCheck;
  private JCheckBox mwCheck;

  MouseAttributesPanel(SessionConfig config) {
    super(config, "Mouse");
  }

  /**Component initialization*/
  public void initPanel() throws Exception {

    setLayout(new BorderLayout());
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    add(contentPane, BorderLayout.NORTH);

    // define double click as enter
    JPanel dcep = new JPanel();
    dcep.setBorder(BorderFactory.createTitledBorder(LangTool.getString("sa.doubleClick")));

    dceCheck = new JCheckBox(LangTool.getString("sa.sendEnter"));

    // check if double click sends enter
    dceCheck.setSelected(getStringProperty("doubleClick").equals("Yes"));

    dcep.add(dceCheck);

    // define double click as enter
    JPanel mwp = new JPanel();
    mwp.setBorder(BorderFactory.createTitledBorder(LangTool.getString("sa.mouseWheel")));

    mwCheck = new JCheckBox(LangTool.getString("sa.activateMW"));

    // check if mouse wheel active
    mwCheck.setSelected(getStringProperty("mouseWheel").equals("Yes"));

    mwp.add(mwCheck);

    contentPane.add(dcep);
    contentPane.add(mwp);

  }

  public void applyAttributes() {

    //  double click enter
    if (dceCheck.isSelected()) {
      changes.firePropertyChange(this, "doubleClick",
          getStringProperty("doubleClick"),
          "Yes");
      setProperty("doubleClick", "Yes");
    } else {
      changes.firePropertyChange(this, "doubleClick",
          getStringProperty("doubleClick"),
          "No");
      setProperty("doubleClick", "No");
    }

    if (mwCheck.isSelected()) {
      changes.firePropertyChange(this, "mouseWheel",
          getStringProperty("mouseWheel"),
          "Yes");
      setProperty("mouseWheel", "Yes");
    } else {
      changes.firePropertyChange(this, "mouseWheel",
          getStringProperty("mouseWheel"),
          "No");
      setProperty("mouseWheel", "No");
    }

  }
}
