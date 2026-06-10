/**
 * Clase: ExecuteScriptAction.java
 * Descripción: Acción Swing que ejecuta un archivo de script sobre la sesión 5250 activa
 * al ser invocada, utilizando el gestor de intérpretes disponible.
 */
package com.ide.as400.scripting;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;
import com.ide.as400.SessionPanel;

public class ExecuteScriptAction extends AbstractAction {

	private static final long serialVersionUID = 181938308216785668L;
	private static final transient TN5250jLogger LOG = TN5250jLogFactory.getLogger(ExecuteScriptAction.class);

	private String _scriptFile;
	private SessionPanel ses;

   public ExecuteScriptAction(String name, String scriptFile, SessionPanel session) {
      super(name);
      _scriptFile = scriptFile;
      ses = session;
   }

   public void actionPerformed(ActionEvent e) {
	   if (LOG.isDebugEnabled()) {
		   LOG.debug("Invoking " + _scriptFile);
	   }

      try {
         InterpreterDriverManager.executeScriptFile(ses,_scriptFile);
      }
      catch (InterpreterDriver.InterpreterException ex) {
         ses.setMacroRunning(false);
         System.out.println(ex);
         ex.printStackTrace();
      }
   }
}
