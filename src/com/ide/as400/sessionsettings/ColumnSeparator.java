package com.ide.as400.sessionsettings;

/**
 * Clase: ColumnSeparator.java
 * Descripción: Enumeración que define los tipos de estilo de línea separadora de columnas
 * disponibles en la pantalla 5250 (oculto, punto, línea y línea corta).
 */
public enum ColumnSeparator {

  Hide, Dot, Line, ShortLine;

  /**
   * searches the enumeration for the given name, case insensitive
   *
   * @param name name
   * @return the corresponding enum value OR default value, if name not matches
   */
  public static ColumnSeparator getFromName(String name) {
    ColumnSeparator result = DEFAULT;
    if (name == null) return result;
    for (ColumnSeparator sep : ColumnSeparator.values()) {
      if (name.equalsIgnoreCase(sep.toString())) {
        return sep;
      }
    }
    return result;
  }

  /**
   * default Line
   */
  public static ColumnSeparator DEFAULT = Hide;
}
