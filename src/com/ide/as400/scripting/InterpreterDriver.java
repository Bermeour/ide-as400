/**
 * Clase: InterpreterDriver.java
 * Descripción: Interfaz que deben implementar todos los controladores de lenguajes de scripting,
 * definiendo los métodos para ejecutar scripts como cadena de texto o desde archivo sobre una sesión.
 */
package com.ide.as400.scripting;
import com.ide.as400.SessionPanel;

public interface InterpreterDriver  {
   /**
    * Execute a script string.
    * @param session a session object
    * @param script script a string to be interpreted
    * @throws InterpreterDriver.InterpreterException
    *            which wraps the exception throw by underlying
    *            interpreter
    */
   public void executeScript(SessionPanel session,String script)
            throws InterpreterDriver.InterpreterException;

   /**
    * Execute a script file.
    * @param session a session object
    * @param scriptFile script a name of file to be interpreted
    * @throws InterpreterDriver.InterpreterException
    *            which wraps the exception throw by underlying
    *            interpreter
    */
   public void executeScriptFile(SessionPanel session, String scriptFile)
            throws InterpreterDriver.InterpreterException;

   /**
    * Execute a script file.
    * @param scriptFile a name of file to be interpreted
    * @throws InterpreterDriver.InterpreterException
    *            which wraps the exception throw by underlying
    *            interpreter
    */
   public void executeScriptFile(String scriptFile)
            throws InterpreterDriver.InterpreterException;

   /**
    * Get the extension for supported extensions by this driver
    * @return Array of string containing extension supported
    */
   public String[] getSupportedExtensions();

   /**
    * Get the langauges for supported extensions by this driver
    * @return Array of string containing languages supported
    */
   public String[] getSupportedLanguages();

   /**
   * Nested class for wrapping the exception throw by underlying
   * interpreter while executing scripts
   */
   public static class InterpreterException extends Exception {
      private static final long serialVersionUID = 1L;
	private Exception _underlyingException;

   /**
    * Construct a wrapper exception for given undelying exception.
    * @param ex the underlying exception thrown by the interpreter
    */
   public InterpreterException(Exception ex) {
       _underlyingException = ex;
   }

   /**
    * Get a string representation for this object
    * @return string representing the object
    */
   public String toString() {
       return "InterpreterException: underlying exception: "
      + _underlyingException;
   }
    }
}
