/**
 * Clase: SessionManagerInterface.java
 * Descripción: Interfaz que define las operaciones del gestor de sesiones del emulador, permitiendo abrir nuevas sesiones, cerrarlas y obtener el conjunto de sesiones activas.
 */
package com.ide.as400.interfaces;

import java.util.Properties;

import com.ide.as400.framework.common.Sessions;
import com.ide.as400.Session5250;
import com.ide.as400.SessionPanel;

public interface SessionManagerInterface {

	/**
	 * @return sessions
	 */
	public abstract Sessions getSessions();

	/**
	 * @param sessionObject a panel object
	 */
	public abstract void closeSession(SessionPanel sessionObject);

	/**
	 * @param props properties
	 * @param configurationResource a configuration
	 * @param sessionName a session name
	 * @return 5250 session object
	 */
	public abstract Session5250 openSession(Properties props, String configurationResource, String sessionName);

}
