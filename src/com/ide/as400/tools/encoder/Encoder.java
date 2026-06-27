/**
 * Clase: Encoder.java
 * Descripción: Interfaz que define el contrato para los codificadores de imágenes, especificando
 * el método para codificar un componente Swing en un stream de salida.
 */
package com.ide.as400.tools.encoder;

import java.awt.Component;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Interface that defines an encoder
 */
public interface Encoder {
   /**
    * Encode the specified component on the specified stream
    * 
    * @param component component
    * @param stream an outputstream
    * @throws IOException an exception
    * @throws EncoderException an encoder exception
    */
   public void encode(Component component, OutputStream stream) throws IOException, EncoderException;

}
