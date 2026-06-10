/**
 * Clase: JavaCodePageTest.java
 * Descripción: Prueba la conversión bidireccional entre caracteres Unicode y EBCDIC usando
 * JavaCodePageFactory, verificando la página de código ASCII y el manejo de páginas inexistentes.
 */
package com.ide.as400.encoding;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaCodePageTest {

	/**
	 * Test method for {@link com.ide.as400.encoding.JavaCodePageFactory#ebcdic2uni(int)}.
	 */
	@Test
	public void testEbcdic2uni() {
		ICodePage jcp = JavaCodePageFactory.getCodePage("ASCII");
		assertNotNull("At least an ASCII Codepage should be available.", jcp);

		char actual = jcp.ebcdic2uni(97);
		assertEquals("simple test for character 'a'", 'a', actual);
	}

	/**
	 * Test method for {@link com.ide.as400.encoding.JavaCodePageFactory#uni2ebcdic(char)}.
	 */
	@Test
	public void testUni2ebcdic() {
		ICodePage jcp = JavaCodePageFactory.getCodePage("ASCII");
		assertNotNull("At least an ASCII Codepage should be available.", jcp);

		byte actual = jcp.uni2ebcdic('a');
		assertEquals("simple test for character 'a' = bytecode 97", 97, actual);
	}

	/**
	 * Test for a not existing codepage
	 */
	@Test
	public void testNotExistingCodePage() {
		ICodePage jcp = JavaCodePageFactory.getCodePage("FOOBAR");
		assertNull("There should be no such Codepage available", jcp);
	}
}
