package com.ide.as400.sessionsettings;

import com.ide.as400.tools.LangTool;
import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import com.ide.as400.SessionConfig;

/**
 * Clase: AttributesPanel.java
 * Descripción: Clase base abstracta para todos los paneles de atributos de configuración de sesión,
 * que proporciona métodos comunes de acceso y modificación de propiedades de sesión.
 */
abstract class AttributesPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private static final String nodePrefix = "sa.node";

  private final TN5250jLogger log = TN5250jLogFactory.getLogger (this.getClass());

  private String name;
  SessionConfig changes = null;
  // content pane to be used if needed by subclasses
  JPanel contentPane;

  public AttributesPanel(SessionConfig config) {
    this(config, "", nodePrefix);
  }

  public AttributesPanel(SessionConfig config, String name) {
    this(config, name, nodePrefix);
  }

  public AttributesPanel(SessionConfig config, String name, String prefix) {
    super();
    changes = config;
    this.name = LangTool.getString(prefix + name);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    try {
      initPanel();
    } catch (Exception e) {
      log.error(e);
    }
  }

  public abstract void initPanel() throws Exception;

  public abstract void applyAttributes();

  protected final String getStringProperty(String prop) {

    if (changes.isPropertyExists(prop))
      return changes.getStringProperty(prop);
    else
      return "";

  }

  protected final String getStringProperty(String prop, String defaultValue) {

    if (changes.isPropertyExists(prop)) {
      String p = changes.getStringProperty(prop);
      if (p.length() > 0)
        return p;
      else
        return defaultValue;
    } else
      return defaultValue;

  }

  protected final Color getColorProperty(String prop) {

    if (changes.isPropertyExists(prop)) {
      Color c = new Color(changes.getIntegerProperty(prop));
      return c;
    } else
      return null;

  }

  protected Color getColorProperty(String prop, Color defColor) {

    if (changes.isPropertyExists(prop)) {
      Color c = new Color(changes.getIntegerProperty(prop));
      return c;
    } else
      return defColor;

  }

  protected final boolean getBooleanProperty(String prop, boolean dflt) {

    if (changes.isPropertyExists(prop)) {
      String b = changes.getStringProperty(prop).toLowerCase();
      if (b.equals("yes") || b.equals("true"))
        return true;
      else
        return false;
    } else
      return dflt;

  }

  protected Rectangle getRectangleProperty(String key) {
    return changes.getRectangleProperty(key);
  }

  protected void setRectangleProperty(String key, Rectangle rect) {
    changes.setRectangleProperty(key, rect);
  }

  protected final void setProperty(String key, String val) {
    changes.setProperty(key, val);
  }

  public String toString() {
    return name;
  }

}
