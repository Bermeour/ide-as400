/**
 * Clase: TN5250jLogger.java
 * Descripción: Interfaz genérica de logging que define los niveles de mensaje y los métodos
 * abstractos que deben implementar todos los loggers del sistema (debug, info, warn, error, fatal).
 */
package com.ide.as400.tools.logging;

/**
 * An interface defining generic loggers.
 */
public interface TN5250jLogger {

	// debug levels - The levels work from lower to higher. The lower levels
	// will be activated by turning on a higher level
	public static final int DEBUG = 1;	// most verbose
	public static final int INFO  = 2;
	public static final int WARN  = 4;  // medium verbose, should be choosen for deployment
	public static final int ERROR = 8;
	public static final int FATAL = 16;
	public static final int OFF   = 32;  // most silence

	/**
	 * @param clazz a class name
	 */
	abstract public void initialize(final String clazz);

	/**
	 * @param message a message object
	 */
	abstract public void debug(Object message);

	/**
	 * @param message a message object
	 * @param throwable a throwable
	 */
	abstract public void debug(Object message, Throwable throwable);

	abstract public void info(Object message);

	/**
	 * @param message a message object
	 * @param throwable a throwable
	 */
	abstract public void info(Object message, Throwable throwable);

	/**
	 * @param message a message object
	 */
	abstract public void warn(Object message);

	/**
	 * @param message message object
	 * @param throwable a throwable object
	 */
	abstract public void warn(Object message, Throwable throwable);

	/**
	 * @param message a message object
	 */
	abstract public void error(Object message);

	/**
	 * @param message a message object
	 * @param throwable a throwable object
	 */
	abstract public void error(Object message, Throwable throwable);

	/**
	 * @param message a message object
	 */
	abstract public void fatal(Object message);

	/**
	 * @param message a message object
	 * @param throwable a throwable object
	 */
	abstract public void fatal(Object message, Throwable throwable);

	/**
	 * @return flag
	 */
	abstract public boolean isDebugEnabled();

	/**
	 * @return flag
	 */
	abstract public boolean isInfoEnabled();

	/**
	 * @return flag
	 */
	abstract public boolean isWarnEnabled();

	/**
	 * @return flag
	 */
	abstract public boolean isErrorEnabled();

	/**
	 * @return flag
	 */
	abstract public boolean isFatalEnabled();

	/**
	 * Sets a new log level.
	 * @param newLevel level
	 */
	abstract public void setLevel(int newLevel);

	/**
	 * @return The current log level.
	 */
	abstract public int getLevel();

}
