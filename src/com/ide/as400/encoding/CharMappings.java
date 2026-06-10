/**
 * Clase: CharMappings.java
 * Descripción: Punto de acceso centralizado para la obtención de páginas de código EBCDIC, consultando en orden las fábricas integrada, de Toolbox y de Java, con caché de instancias ya creadas.
 */
package com.ide.as400.encoding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Character Mappings for EBCDIC to ASCII and ASCII to EBCDIC translations
 */
public class CharMappings {

	public static final String DFT_ENC = "37";
	public static final int NATIVE_CP = 0;
	public static final int TOOLBOX_CP = 1;

	private static final HashMap<String,ICodePage> map = new HashMap<String, ICodePage>();

	public static String[] getAvailableCodePages() {
		Set<String> cpset = new HashSet<String>(); // no double entries
		for (String cp : BuiltInCodePageFactory.getInstance().getAvailableCodePages()) {
			cpset.add(cp);
		}
		for (String cp : ToolboxCodePageFactory.getInstance().getAvailableCodePages()) {
			cpset.add(cp);
		}
		String[] cparray = cpset.toArray(new String[cpset.size()]);
		Arrays.sort(cparray);
		return cparray;
	}

	public static ICodePage getCodePage(String encoding) {
		if (map.containsKey(encoding)) {
			return map.get(encoding);
		}

		ICodePage cp = BuiltInCodePageFactory.getInstance().getCodePage(encoding);
		if (cp != null) {
			map.put(encoding, cp);
			return cp;
		}

		cp = ToolboxCodePageFactory.getInstance().getCodePage(encoding);
		if (cp != null) {
			map.put(encoding, cp);
			return cp;
		}

		cp = JavaCodePageFactory.getCodePage(encoding);
		if (cp != null) {
			map.put(encoding, cp);
			return cp;
		}

		// unsupported codepage ==> return default
		return BuiltInCodePageFactory.getInstance().getCodePage(DFT_ENC);
	}

}
