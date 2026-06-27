/**
 * Clase: CCSID278Test.java
 * Descripción: Prueba la correctitud de la conversión byte EBCDIC↔Unicode para la página de código CCSID 278
 * (EBCDIC finlandés/sueco), comparando la implementación clásica con la nueva implementación CCSID278.
 */
package com.ide.as400.encoding.builtin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import com.ide.as400.encoding.CharMappings;
import com.ide.as400.encoding.ICodePage;
import com.ide.as400.encoding.builtin.CCSID278;

public class CCSID278Test {

	/**
	 * Correctness test for old implementation ....
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testOldConverter278() {

		ICodePage cp = CharMappings.getCodePage("278");
		assertNotNull("At least an ASCII Codepage should be available.", cp);

		for (int i=0; i<256; i++) {
			final byte beginvalue = (byte)i;
			final char converted = cp.ebcdic2uni(beginvalue);
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing item #" + i, beginvalue, afterall);
		}

	}

	/**
	 * Correctness test for new implementation ...
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testNewConverter278() {
		CCSID278 cp = new CCSID278();
		cp.init();
		assertNotNull("At least an ASCII Codepage should be available.", cp);

		for (int i=0; i<256; i++) {
			final byte beginvalue = (byte)i;
			final char converted = cp.ebcdic2uni(beginvalue);
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing item #" + i, beginvalue, afterall);
		}
	}

	/**
	 * Testing for Correctness both implementations ...
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testBoth() {
		final ICodePage cp = CharMappings.getCodePage("278");
		final CCSID278 cpex = new CCSID278();
		cpex.init();
		assertNotNull("At least an ASCII Codepage should be available.", cpex);

		for (int i=0; i<256; i++) {
			final byte beginvalue = (byte)i;
			assertEquals("Testing to EBCDIC item #" + i, cp.ebcdic2uni(beginvalue), cpex.ebcdic2uni(beginvalue));
			final char converted = cp.ebcdic2uni(beginvalue);
			assertEquals("Testing to UNICODE item #" + i, cp.uni2ebcdic(converted), cpex.uni2ebcdic(converted));
			final byte afterall = cp.uni2ebcdic(converted);
			assertEquals("Testing before and after item #" + i, beginvalue, afterall);
		}
	}

}
