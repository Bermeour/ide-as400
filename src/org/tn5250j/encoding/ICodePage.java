package org.tn5250j.encoding;

public interface ICodePage {

	/**
	 * Convert a single byte (or maybe more bytes which representing one character) to a Unicode character.
	 *
	 * @param index a byte
	 * @return a character
	 */
	public abstract char ebcdic2uni(int index);

	/**
	 * Convert a Unicode character in it's byte representation.
	 * Therefore, only 8bit codepages are supported.
	 *
	 * @param index a character
	 * @return a byte
	 */
	public abstract byte uni2ebcdic(char index);

}
