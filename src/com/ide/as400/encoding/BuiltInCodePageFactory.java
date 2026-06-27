/**
 * Clase: BuiltInCodePageFactory.java
 * Descripción: Fábrica singleton que registra y proporciona acceso a los conversores de página de código EBCDIC integrados en el emulador, como CCSID 37, 273, 500, entre otros.
 */
package com.ide.as400.encoding;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.ide.as400.encoding.builtin.CCSID1025;
import com.ide.as400.encoding.builtin.CCSID1026;
import com.ide.as400.encoding.builtin.CCSID1112;
import com.ide.as400.encoding.builtin.CCSID1140;
import com.ide.as400.encoding.builtin.CCSID1141;
import com.ide.as400.encoding.builtin.CCSID1147;
import com.ide.as400.encoding.builtin.CCSID1148;
import com.ide.as400.encoding.builtin.CCSID273;
import com.ide.as400.encoding.builtin.CCSID277;
import com.ide.as400.encoding.builtin.CCSID278;
import com.ide.as400.encoding.builtin.CCSID280;
import com.ide.as400.encoding.builtin.CCSID284;
import com.ide.as400.encoding.builtin.CCSID285;
import com.ide.as400.encoding.builtin.CCSID297;
import com.ide.as400.encoding.builtin.CCSID37;
import com.ide.as400.encoding.builtin.CCSID424;
import com.ide.as400.encoding.builtin.CCSID500;
import com.ide.as400.encoding.builtin.CCSID870;
import com.ide.as400.encoding.builtin.CCSID871;
import com.ide.as400.encoding.builtin.CCSID875;
import com.ide.as400.encoding.builtin.ICodepageConverter;
import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;

/**
 * Methods for built-in code page support.
 */
/* package */ class BuiltInCodePageFactory {

	private static BuiltInCodePageFactory singleton;

	private final List<Class<?>> clazzes = new ArrayList<Class<?>>();
	private final TN5250jLogger log = TN5250jLogFactory.getLogger(this.getClass());

	private BuiltInCodePageFactory() {
		register();
	}

	public static synchronized final BuiltInCodePageFactory getInstance() {
		if (singleton == null) {
			singleton = new BuiltInCodePageFactory();
		}
		return singleton;
	}

	private void register() {
		clazzes.add(CCSID37.class);
		clazzes.add(CCSID273.class);
		clazzes.add(CCSID277.class);
		clazzes.add(CCSID278.class);
		clazzes.add(CCSID280.class);
		clazzes.add(CCSID284.class);
		clazzes.add(CCSID285.class);
		clazzes.add(CCSID297.class);
		clazzes.add(CCSID424.class);
		clazzes.add(CCSID500.class);
		clazzes.add(CCSID870.class);
		clazzes.add(CCSID871.class);
		clazzes.add(CCSID875.class);
		clazzes.add(CCSID1025.class);
		clazzes.add(CCSID1026.class);
		clazzes.add(CCSID1112.class);
		clazzes.add(CCSID1140.class);
		clazzes.add(CCSID1141.class);
		clazzes.add(CCSID1147.class);
		clazzes.add(CCSID1148.class);
	}

	/**
	 * @return unsorted list of available code pages
	 */
	public String[] getAvailableCodePages() {
		HashSet<String> cpset = new HashSet<String>();
		for (Class<?> clazz : clazzes) {
			final ICodepageConverter converter = getConverterFromClassName(clazz);
			if (converter != null) {
				cpset.add(converter.getName());
			}
		}
		return cpset.toArray(new String[cpset.size()]);
	}

	/**
	 * @param encoding
	 * @return an {@link ICodePage} object OR null, of not found
	 */
	public ICodePage getCodePage(String encoding) {
		for (Class<?> clazz : clazzes) {
			final ICodepageConverter converter = getConverterFromClassName(clazz);
			if (converter != null && converter.getName().equals(encoding)) {
				return converter;
			}
		}
		return null;
	}

	/**
	 * Lazy loading converters takes time,
	 * but doesn't happen so often and saves memory.
	 *
	 * @param clazz {@link ICodepageConverter}
	 * @return
	 */
	private ICodepageConverter getConverterFromClassName(Class<?> clazz) {
		try {
			final Constructor<?> constructor = clazz.getConstructor(new Class[0]);
			final ICodepageConverter converter = (ICodepageConverter) constructor.newInstance();
			converter.init();
			return converter;
		} catch (Exception e) {
			log.error("Couldn't load code page converter class:" + clazz.getCanonicalName(), e);
			return null;
		}
	}

}
