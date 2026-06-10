package com.ide.as400.encoding.builtin;

import com.ide.as400.encoding.ICodePage;

/**
 * Clase: ICodepageConverter.java
 * Descripción: Interfaz extendida de ICodePage para conversores de página de código integrados, añadiendo métodos para obtener el nombre identificador, la descripción y la inicialización del conversor.
 */
public interface ICodepageConverter extends ICodePage {

	/**
	 * Returns an name/ID for this converter.
	 * Example '273' or 'CP1252'. This name should be unique,
	 * cause it's used in user settungs and so on.
	 *
	 * @return a String
	 */
	public abstract String getName();

	/**
	 * Returns a short description for this converter.
	 * For Example '273 - German, EBCDIC'
	 *
	 * @return a String
	 */
	public abstract String getDescription();

	/**
	 * Does special initialization stuff for this converter.
	 * 
	 * @return a converter
	 */
	public abstract ICodepageConverter init();

}
