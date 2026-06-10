/**
 * Clase: EncodeComponent.java
 * Descripción: Clase de utilidad que permite codificar componentes Swing en distintos formatos de
 * imagen (actualmente PNG) y escribir el resultado en un stream o archivo de salida.
 */
package com.ide.as400.tools.encoder;

import java.awt.Component;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Used to encode components into different image file formats. ie .GIF and .PNG
 */
public class EncodeComponent {

   /** specifies PNG encoding */
   public static final Encoding PNG = new Encoding("PNG",
      "Portable Network Graphics",
      "com.ide.as400.tools.encoder.PNGEncoder",
      " PNG Load Error");

   public static final Encoding ENCODINGS[] = {
      PNG
   };

   /**
    * Invoke this methods on Java components to encode their image into the
    * specified format
    * @param component component to encode
    * @param encoding type of encoding to use (Currently GIF or PNG)
    * @param output stream to which to write the encoding
    * 
    * @throws IOException an io exception
    * @throws EncoderException a special encoder exception
    */
   public static void encode(Encoding encoding, Component component, OutputStream output) throws IOException, EncoderException {
      Encoder encoder = encoding.getEncoder();
      if (encoder == null) {
   	   throw new EncoderException("Graphics Encoder could not be loaded.");
      }
      encoder.encode(component, output);
   }

   /**
    * Invoke this methods on Java components to encode their image into the
    * specified format
    * @param component component to encode
    * @param encoding type of encoding to use (GIF, PNG, JPEG, EPS, PS, PDF,
    * or PCL)
    * @param file file to which to write the encoding
    * 
    * @throws IOException an io exception
    * @throws EncoderException a special encoder exception 
    */
   public static void encode(Encoding encoding, Component component, File file) throws IOException, EncoderException {
      OutputStream os = new java.io.FileOutputStream(file);
      encode(encoding, component, os);
      os.close();
   }

      /**
      * Class used to enumerate valid encodings
      */
      public static class Encoding {
         private String shortName;
         private String longName;
         private String encoderClass;
         private String failureMessage;

         private Encoder encoder;

          /**
           * Constructs and new image encoder.
           * 
           * @param short_name name
           * @param long_name name
           * @param encoder_class classname
           * @param failure_message message
           */
         public Encoding(String short_name, String long_name,
                     String encoder_class, String failure_message) {
            this.shortName = short_name;
            this.longName = long_name;
            this.encoderClass = encoder_class;
            this.failureMessage = failure_message;
         }

         /**
          * Return the short brief name of the supported encoding type
          * 
          * @return string
          */
         public String getShortName() {
            return shortName;
         }

         /**
          * Return the long name of the supported encoding type
          * 
          * @return string
          */
         public String getLongName() {
            return longName;
         }

          /**
           * Returns a string representation bassed on the long and short name of the
           * Encoder.
           * 
           * @return string
           */
         public String toString() {
            return getLongName() + " (" + getShortName() + ")";
         }

         /**
          * Message to return about possible reasons for encoder load
          * failure (i.e. getEncoder() returns null)
          * 
          * @return string
          */
         public String getFailureMessage() {
            return failureMessage != null ? failureMessage : "There was a failure loading encoder.";
         }

         /**
          * Return an encoder for this encoding type.
          * 
          * @return returns null if it cannot locate or load the encoder
          */
         public Encoder getEncoder() {
            if (encoder == null) {
               // encoder null so attempt to load it
               Class<?> encoder_class = null;
               try {
                  encoder_class = Class.forName(encoderClass);
               }
               catch (ClassNotFoundException cnfe) {
               }

               if (encoder_class != null) {
                  try {
                     encoder = (Encoder) encoder_class.newInstance();
                  }
                  catch (InstantiationException ie) {
                  }
                  catch (IllegalAccessException iae) {
                  }
               }
            }
            return encoder;
         }

      } // End of Encoding inner class

}
