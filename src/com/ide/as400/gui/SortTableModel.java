/**
 * Clase: SortTableModel.java
 * Descripción: Interfaz que extiende TableModel para indicar si una columna es ordenable y ejecutar el
 * ordenamiento de sus datos de forma ascendente o descendente.
 */
package com.ide.as400.gui;

import javax.swing.table.TableModel;

public interface SortTableModel extends TableModel {
   public boolean isSortable(int col);
   public void sortColumn(int col, boolean ascending);
}
