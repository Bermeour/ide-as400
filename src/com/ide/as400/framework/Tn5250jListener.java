/**
 * Clase: Tn5250jListener.java
 * Descripción: Clase abstracta que define el contrato que deben implementar los módulos o plugins
 * del framework TN5250j, incluyendo los métodos de ciclo de vida (init, run, destroy) y la
 * recepción de eventos de sesión y teclado.
 */
package com.ide.as400.framework;

import java.io.File;
import java.util.Properties;

public abstract class Tn5250jListener {
	public abstract void actionPerformed(Tn5250jEvent event);

	public abstract void init(File fileDir, Properties config);

	public abstract void run();

	public abstract void destroy();

	public abstract String getName();

	public abstract void setController(Tn5250jController control);

	public abstract void sessionCreated(Tn5250jSession session);
}
