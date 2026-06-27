/**
 * Clase: KeyStrokenizer.java
 * Descripción: Analizador léxico de secuencias de pulsaciones de teclado que interpreta
 * cadenas de texto donde los mnemónicos de teclas especiales están delimitados por corchetes
 * (por ejemplo, [PF1]), permitiendo iterar tecla a tecla sobre la secuencia completa.
 */
package com.ide.as400.framework.tn5250;

import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;


public class KeyStrokenizer {

   private StringBuffer keyStrokes;
   private StringBuffer sb;
   private int index;
   private int length;

   private final TN5250jLogger log = TN5250jLogFactory.getLogger(this.getClass());

   public KeyStrokenizer() {

      sb = new StringBuffer();
      setKeyStrokes(null);
   }

   public void setKeyStrokes (String strokes) {

      if (strokes != null) {
         keyStrokes.setLength(0);
		 log.debug("set "+ keyStrokes);
         length = strokes.length();
      }
      else {

         keyStrokes = new StringBuffer();
         length = 0;

      }
      keyStrokes.append(strokes);
      index = 0;

   }

   public boolean hasMoreKeyStrokes() {
      return length > index;
   }

   public String nextKeyStroke() {

      String s = "";
      boolean gotOne = false;
      if(length > index) {
         sb.setLength(0);

         char c = keyStrokes.charAt(index);
         switch(c) {
            case '[':
               sb.append(c);
               index++;

               // we need to throw an error here
               if(index >= length) {
                  log.warn(" mnemonic key was incomplete :1 " +
                                       "at position " + index + " len " + length );
               }
               else {
                  c = keyStrokes.charAt(index);

                  if(c == '[')
                       index++;
                  else {
                     while(!gotOne) {

                        if(c == ']') { // did we find an ending
                           sb.append(c);
                           index++;
                           gotOne = true;
                        }
                        else {
                           sb.append(c);
                           index++;
                           // we need to throw an error here because we did not
                           //   find an ending for the potential mnemonic
                           if(index >= length) {
                              log.warn(
                              " mnemonic key was incomplete ending not found :2 " +
                                          "at position " + index);
                           }
                           c = keyStrokes.charAt(index);
                        }
                     }
                  }
               }
               break;

            case ']':
               index++;
               if(index >= length) {
                  log.warn(
                  " mnemonic key was incomplete ending not found :3 " +
                              "at position " + index);
                  sb.append(c);
                  index++;

               }
               else {
                  c = keyStrokes.charAt(index);
                  if(c == ']') {
                     sb.append(c);
                     index++;
                  }
                  else {
                     log.warn(
                     " mnemonic key was incomplete beginning not found :4 " +
                                 "at position " + index);
                  }
               }
               break;
            default:
               sb.append(c);
               index++;
               break;
         }
         if(sb != null) {
            s = new String(sb);
         }

      }
	  log.debug("next "+ keyStrokes);

      return s;
   }

   public String getUnprocessedKeyStroked() {
      if(index >= length) {
    	  return null;
      }
      return keyStrokes.substring(index);
   }

}
