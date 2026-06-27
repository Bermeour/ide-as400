/**
 * Clase: ColumnComparator.java
 * Descripción: Comparador genérico de columnas de tabla que ordena vectores de filas de forma ascendente
 * o descendente según el índice de columna especificado.
 */
package com.ide.as400.gui;

import java.util.*;

public class ColumnComparator implements Comparator {
   protected int index;
   protected boolean ascending;

   public ColumnComparator(int index, boolean ascending) {
      this.index = index;
      this.ascending = ascending;
   }

   public int compare(Object one, Object two) {
      if (one instanceof Vector && two instanceof Vector) {
         Vector vOne = (Vector)one;
         Vector vTwo = (Vector)two;
         Object oOne = vOne.elementAt(index);
         Object oTwo = vTwo.elementAt(index);
         if (oOne instanceof Comparable && oTwo instanceof Comparable) {
            Comparable cOne = (Comparable)oOne;
            Comparable cTwo = (Comparable)oTwo;
            if (ascending) {
               return cOne.compareTo(cTwo);
            }
            else {
               return cTwo.compareTo(cOne);
            }
         }
      }

      return 1;
   }
}
