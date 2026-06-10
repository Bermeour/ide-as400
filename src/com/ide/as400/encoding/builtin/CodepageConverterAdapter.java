/**
 * Clase: CodepageConverterAdapter.java
 * Descripción: Clase adaptador abstracta para conversores de página de código de 8 bits, construye en su inicialización una tabla inversa para acelerar la conversión de Unicode a EBCDIC.
 */
package com.ide.as400.encoding.builtin;

import java.util.Arrays;

/**
 * Adapter class for converters using 8bit codepages.
 *
 * @author master_jaf
 */
public abstract class CodepageConverterAdapter implements ICodepageConverter {

	private char[] codepage = null;
	private int[] reverse_codepage = null;

	/* (non-Javadoc)
	 * @see com.ide.as400.cp.ICodepageConverter#init()
	 */
	public ICodepageConverter init() {
		codepage = getCodePage();

		int size = 0;
		for (int i=0; i<codepage.length; i++) {
			size = Math.max(size, codepage[i]);
		}
		assert (size + 1) < 1024*1024; // some kind of maximum size limiter.
		reverse_codepage = new int[size+1];
		Arrays.fill(reverse_codepage, '?');
		for (int i=0; i<codepage.length; i++) {
			reverse_codepage[codepage[i]] = i;
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.ide.as400.cp.ICodepageConverter#uni2ebcdic(char)
	 */
	public byte uni2ebcdic(char index) {
		assert index < reverse_codepage.length;
		return (byte)reverse_codepage[index];
	}

	/* (non-Javadoc)
	 * @see com.ide.as400.cp.ICodepageConverter#ebcdic2uni(int)
	 */
	public char ebcdic2uni(int index) {
		index = index & 0xFF;
		assert index < 256;
		return codepage[index];
	}

	/**
	 * @return The oringal 8bit codepage.
	 */
	protected abstract char[] getCodePage();

}
