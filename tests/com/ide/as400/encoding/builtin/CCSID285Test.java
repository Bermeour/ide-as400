/**
 * Clase: CCSID285Test.java
 * Descripción: Prueba la correctitud de la conversión Unicode↔EBCDIC para la página de código CCSID 285
 * (EBCDIC inglés del Reino Unido), comparando la implementación clásica con la nueva implementación CCSID285.
 */
package com.ide.as400.encoding.builtin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import com.ide.as400.encoding.CharMappings;
import com.ide.as400.encoding.ICodePage;
import com.ide.as400.encoding.builtin.CCSID285;

public class CCSID285Test {

	private char[] TESTSTRING = new char[255];

	@Before
	public void setUp() {
		for (int i=1; i<=255; i++) {
			TESTSTRING[i-1] = (char) i;
		}
	}

	/**
	 * Correctness test for old implementation ....
	 */
	@Test
	public void testOldConverter285() {

		ICodePage cp = CharMappings.getCodePage("285");
		assertNotNull("At least an ASCII Codepage should be available.", cp);

		for (int i=0; i<TESTSTRING.length; i++) {
			final char beginvalue = TESTSTRING[i];
			final byte converted = cp.uni2ebcdic(beginvalue);
			final char afterall = cp.ebcdic2uni(converted & 0xFF);
			assertEquals("Testing item #" + i, beginvalue, afterall);
		}

	}

	/**
	 * Correctness test for new implementation ...
	 */
	@Test
	public void testNewConverter285() {
		CCSID285 cp = new CCSID285();
		cp.init();
		assertNotNull("At least an ASCII Codepage should be available.", cp);

		for (int i=0; i<TESTSTRING.length; i++) {
			final char beginvalue = TESTSTRING[i];
			final byte converted = cp.uni2ebcdic(beginvalue);
			final char afterall = cp.ebcdic2uni(converted & 0xFF);
			assertEquals("Testing item #" + i, beginvalue, afterall);
		}
	}

	/**
	 * Testing for Correctness both implementations ...
	 */
	@Test
	public void testBoth() {
		final ICodePage cp = CharMappings.getCodePage("285");
		final CCSID285 cpex = new CCSID285();
		cpex.init();
		assertNotNull("At least an ASCII Codepage should be available.", cpex);

		for (int i=0; i<TESTSTRING.length; i++) {

			final char beginvalue = TESTSTRING[i];
			assertEquals("Testing to EBCDIC item #" + i, cp.uni2ebcdic(beginvalue), cpex.uni2ebcdic(beginvalue));
			final byte converted = cp.uni2ebcdic(beginvalue);
			assertEquals("Testing to UNICODE item #" + i, cp.ebcdic2uni(converted & 0xFF), cpex.ebcdic2uni(converted & 0xFF));
			final char afterall = cp.ebcdic2uni(converted & 0xFF);
			assertEquals("Testing before and after item #" + i, beginvalue, afterall);
		}
	}

}
