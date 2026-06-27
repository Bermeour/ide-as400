package com.ide.as400.sessionsettings;

/**
 * Clase: OnConnectAttributesPanel.java
 * Descripción: Panel de configuración que permite especificar una macro a ejecutar
 * automáticamente al establecer la conexión con el sistema AS/400.
 */

import com.ide.as400.tools.LangTool;

import javax.swing.*;
import java.awt.*;
import com.ide.as400.SessionConfig;

class OnConnectAttributesPanel extends AttributesPanel {

  private static final long serialVersionUID = 1L;
  private JTextField connectMacro;

  OnConnectAttributesPanel(SessionConfig config) {
    super(config, "OnConnect");
  }

  /**Component initialization*/
  public void initPanel() throws Exception {

    setLayout(new BorderLayout());
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    add(contentPane, BorderLayout.NORTH);

    // define onConnect macro to run
    JPanel ocMacrop = new JPanel();
    ocMacrop.setBorder(BorderFactory.createTitledBorder(LangTool.getString("sa.connectMacro")));

    connectMacro = new JTextField();
    connectMacro.setColumns(30);

    // sets the connect macro
    connectMacro.setText(getStringProperty("connectMacro"));

    ocMacrop.add(connectMacro);
    contentPane.add(ocMacrop);

  }

  public void applyAttributes() {

    changes.firePropertyChange(this, "connectMacro",
        getStringProperty("connectMacro"),
        connectMacro.getText());
    setProperty("connectMacro", connectMacro.getText());

  }
}
