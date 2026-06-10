/**
 * Clase: EncoderException.java
 * Descripción: Excepción comprobada lanzada por los codificadores de imagen cuando se produce
 * cualquier error durante el proceso de codificación.
 */
package com.ide.as400.tools.encoder;

/**
 * This class is an exception that is raised by Encode or one of it's
 * subclasses.  It may also be subclassed for exceptions thrown by subclasses
 * of Encode. It represents any problem encountered while encoding an image.
 * The message is used to state the type of error.
*/
public class EncoderException extends Exception {
   private static final long serialVersionUID = 1L;

/**
    * Creates an exception with the given message.
    * 
    * @param msg message
    */
   public EncoderException(String msg) {
       super(msg);
   }

}
