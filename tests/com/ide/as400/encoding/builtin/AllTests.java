/**
 * Clase: AllTests.java
 * Descripción: Suite JUnit que agrupa y ejecuta todas las pruebas de páginas de código EBCDIC
 * incorporadas (CCSID), cubriendo las conversiones Unicode de los principales estándares internacionales.
 */
package com.ide.as400.encoding.builtin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses ( {
	CCSID37Test.class,
	CCSID273Test.class,
	CCSID280Test.class,
	CCSID284Test.class,
	CCSID285Test.class,
	CCSID277dkTest.class,
	CCSID277noTest.class,
	CCSID278Test.class,
	CCSID297Test.class,
	CCSID424Test.class,
	CCSID500Test.class,
	CCSID870skTest.class,
	CCSID870plTest.class,
	CCSID871Test.class,
	CCSID875Test.class,
	CCSID1025Test.class,
	CCSID1026Test.class,
	CCSID1112Test.class,
	CCSID1140Test.class,
	CCSID1141Test.class,
	CCSID1147Test.class,
	CCSID1148Test.class,
} )
@RunWith(Suite.class)
public class AllTests {

}
