/**
 * Clase: Tn5250jSession.java
 * Descripción: Encapsula los componentes de una sesión TN5250j activa, agrupando la pantalla 5250,
 * el objeto de protocolo telnet virtual (tnvt) y el panel de sesión para facilitar su acceso
 * desde los módulos listeners del framework.
 */
package com.ide.as400.framework;

import com.ide.as400.framework.tn5250.Screen5250;
//import com.ide.as400.Screen5250;
import com.ide.as400.framework.tn5250.tnvt;
import com.ide.as400.SessionPanel;

public class Tn5250jSession {
	private Screen5250 sessionScreen;
	private tnvt SessionTNVT;
	private SessionPanel session;

	protected Tn5250jSession(Screen5250 screen, tnvt vt, SessionPanel ses) {
		sessionScreen=screen;
		SessionTNVT = vt;
		session=ses;
	}
	/**
	 * @return session object
	 */
	public SessionPanel getSession() {
		return session;
	}

	/**
	 * @return screen object
	 */
	public Screen5250 getSessionScreen() {
		return sessionScreen;
	}

	/**
	 * @return telnet object
	 */
	public tnvt getSessionTNVT() {
		return SessionTNVT;
	}

}
