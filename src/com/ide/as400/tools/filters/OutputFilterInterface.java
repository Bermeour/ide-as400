/**
 * Clase: OutputFilterInterface.java
 * Descripción: Interfaz que define el contrato para los filtros de exportación de datos del AS/400,
 * especificando los métodos para crear el archivo, escribir cabecera, registros y pie de página.
 */
package com.ide.as400.tools.filters;

import java.io.*;
import java.util.ArrayList;

public interface OutputFilterInterface {


   public void createFileInstance(String fileName) throws
                              FileNotFoundException;
   public abstract void writeHeader(String fileName, String host,
                                       ArrayList ffd, char decSep);
   public abstract void writeFooter(ArrayList ffd);
   public abstract void parseFields(byte[] cByte, ArrayList ffd,StringBuffer rb);
   public abstract boolean isCustomizable();
   public abstract void setCustomProperties();
}
