/**
 * Clase: CCSID1026Test.java
 * Descripción: Prueba la correctitud de la conversión byte EBCDIC↔Unicode para la página de código CCSID 1026
 * (EBCDIC turco), comparando la implementación clásica con la nueva implementación CCSID1026.
 */
package com.ide.as400.encoding.builtin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import com.ide.as400.encoding.CharMappings;
import com.ide.as400.encoding.ICodePage;
import com.ide.as400.encoding.builtin.CCSID1026;

public class CCSID1026Test {

	/**
	 * Correctness test for old implementation ....
	 * Testing byte -> Unicode -> byte
	 */
	@Test
	public void testOldConverter1026() {

		ICodePage cp = CharMappings.getCodePage("1026");
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
	public void testNewConverter1026() {
		CCSID1026 cp = new CCSID1026();
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
		final ICodePage cp = CharMappings.getCodePage("1026");
		final CCSID1026 cpex = new CCSID1026();
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
