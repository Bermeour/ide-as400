/**
 * Clase: AbstractCodePage.java
 * Descripción: Clase abstracta base para la traducción entre caracteres EBCDIC y Unicode, implementando la interfaz ICodePage y almacenando el identificador de codificación.
 */
package com.ide.as400.encoding;

/**
 *
 * This class controls the translation from EBCDIC to ASCII and ASCII to EBCDIC
 *
 */
public abstract class AbstractCodePage implements ICodePage {

	protected AbstractCodePage(String encoding) {
		this.encoding = encoding;
	}

	public String getEncoding() {
		return encoding;
	}

	protected String encoding;
}
