package com.ide.as400.interfaces;

import com.ide.as400.event.SessionListener;

/**
 * Clase: SessionInterface.java
 * Descripción: Interfaz que define el contrato para una sesión de emulación AS/400, incluyendo métodos para conectar, desconectar, consultar el estado y registrar listeners de cambio de sesión.
 */
public interface SessionInterface {

	public abstract String getConfigurationResource();

	public abstract boolean isConnected();

	public abstract String getSessionName();

	public abstract int getSessionType();

	public abstract void connect();

	public abstract void disconnect();

	public abstract void addSessionListener(SessionListener listener);

	public abstract void removeSessionListener(SessionListener listener);

	/**
	 * Popups a dialog to ask the user for entering a SysReq value.
	 *
	 * @return null if nothing to do, else a String containing the users input.
	 */
	public abstract String showSystemRequest();

	/**
	 * Signals the user a sound (or maybe a light flash).
	 */
	public abstract void signalBell();

}
