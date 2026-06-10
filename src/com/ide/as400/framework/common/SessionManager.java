/**
 * Clase: SessionManager.java
 * Descripción: Repositorio central Singleton que gestiona todas las sesiones 5250 activas,
 * permitiendo abrir, cerrar y acceder al conjunto de sesiones disponibles en la aplicación.
 */
package com.ide.as400.framework.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ide.as400.interfaces.SessionManagerInterface;
import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;
import com.ide.as400.Session5250;
import com.ide.as400.SessionConfig;
import com.ide.as400.SessionPanel;
import com.ide.as400.TN5250jConstants;


public class SessionManager implements SessionManagerInterface {

	static private Sessions sessions;
	static private List<SessionConfig> configs;

	private TN5250jLogger log = TN5250jLogFactory.getLogger (this.getClass());
	/**
	 * A handle to the unique SessionManager class
	 */
	static private SessionManager _instance;

	/**
	 * The constructor is made protected to allow overriding.
	 */
	protected SessionManager() {
		if (_instance == null) {
			// initialize the settings information
			initialize();
			// set our instance to this one.
			_instance = this;
		}
	}

	/**
	 *
	 * @return The unique instance of this class.
	 */
	static public SessionManager instance() {

		if (_instance == null) {
			_instance = new SessionManager();
		}
		return _instance;

	}

	private void initialize() {
		log.info("New session Manager initialized");
		sessions = new Sessions();
		configs = new ArrayList<SessionConfig>();

	}

	@Override
	public Sessions getSessions() {
		return sessions;
	}

	@Override
	public void closeSession(SessionPanel sesspanel) {

		sesspanel.closeDown();
		sessions.removeSession((sesspanel).getSession());

	}

	@Override
	public synchronized Session5250 openSession(Properties sesProps, String configurationResource
			, String sessionName) {

		if(sessionName == null)
			sesProps.put(TN5250jConstants.SESSION_TERM_NAME,sesProps.getProperty(TN5250jConstants.SESSION_HOST));
		else
			sesProps.put(TN5250jConstants.SESSION_TERM_NAME,sessionName);

		if (configurationResource == null) configurationResource = "";

		sesProps.put(TN5250jConstants.SESSION_CONFIG_RESOURCE, configurationResource);

		SessionConfig useConfig = null;
		for (SessionConfig conf : configs) {
			if (conf.getSessionName().equals(sessionName)) {
				useConfig = conf;
			}
		}

		if (useConfig == null) {

			useConfig = new SessionConfig(configurationResource,sessionName);
			configs.add(useConfig);
		}

		Session5250 newSession = new Session5250(sesProps,configurationResource,
				sessionName,useConfig);
		sessions.addSession(newSession);
		return newSession;

	}

}
