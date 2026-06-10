/**
 * Clase: JavaCodePageFactory.java
 * Descripción: Implementación de ICodePage que utiliza el NIO de Java para realizar conversiones EBCDIC-Unicode mediante un Charset, sirviendo como alternativa cuando no existe un conversor integrado.
 */
package com.ide.as400.encoding;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/* package */ class JavaCodePageFactory extends AbstractCodePage {

	private final CharsetEncoder encoder;
	private final CharsetDecoder decoder;

	/* package */ JavaCodePageFactory(String encoding, CharsetEncoder encoder, CharsetDecoder decoder) {
		super(encoding);
		this.encoder = encoder;
		this.decoder = decoder;
	}

	/* (non-Javadoc)
	 * @see com.ide.as400.encoding.CodePage#ebcdic2uni(int)
	 */
	@Override
	public char ebcdic2uni(int codepoint) {
		try {
			final ByteBuffer in = ByteBuffer.wrap(new byte[] { (byte) codepoint });
			final CharBuffer out = this.decoder.decode(in);
			return out.get(0);
		} catch (Exception cce) {
			return ' ';
		}
	}

	/* (non-Javadoc)
	 * @see com.ide.as400.encoding.CodePage#uni2ebcdic(char)
	 */
	@Override
	public byte uni2ebcdic(char character) {
		try {
			final CharBuffer in = CharBuffer.wrap(new char[] {character});
			final ByteBuffer out = this.encoder.encode(in);
			return out.get(0);
		} catch (Exception cce) {
			return 0x0;
		}
	}

	/**
	 * @param encoding
	 * @return A new {@link CodePage} object OR null, if not available.
	 */
	/* package */ static ICodePage getCodePage(final String encoding) {
		CharsetDecoder dec = null;
		CharsetEncoder enc = null;
		try {
			final Charset cs = java.nio.charset.Charset.forName(encoding);
			dec = cs.newDecoder();
			enc = cs.newEncoder();
		} catch (Exception e) {
			enc = null;
			dec = null;
		}
		if ((enc != null) && (dec != null)) {
			return new JavaCodePageFactory(encoding, enc, dec);
		}
		return null;
	}

}
