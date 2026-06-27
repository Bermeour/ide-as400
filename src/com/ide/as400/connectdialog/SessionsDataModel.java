/**
 * Clase: SessionsDataModel.java
 * Descripción: Modelo de datos simple que representa una fila de sesión con nombre, host y bandera de sesión
 * predeterminada para su uso en el SessionsTableModel.
 */
package com.ide.as400.connectdialog;

class SessionsDataModel {
  final String name;
  final String host;
  final Boolean deflt;

  SessionsDataModel(String name, String host, Boolean deflt) {
    this.name = name;
    this.host = host;
    this.deflt = deflt;
  }
}
