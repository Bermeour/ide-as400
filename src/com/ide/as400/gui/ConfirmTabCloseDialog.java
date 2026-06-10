/**
 * Clase: ConfirmTabCloseDialog.java
 * Descripción: Diálogo modal de confirmación que solicita al usuario verificar si desea cerrar una pestaña,
 * retornando verdadero si se confirma la acción o falso si se cancela.
 */
package com.ide.as400.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ide.as400.tools.LangTool;

public class ConfirmTabCloseDialog {

	private final static String[] OPTIONS = new String[] {  LangTool.getString("key.labelClose"), LangTool.getString("ss.optCancel") };

	private final Component parent;

	private JDialog dialog;
	private JOptionPane pane;


	/**
	 * @param parent component
	 */
	public ConfirmTabCloseDialog(Component parent) {
		super();
		this.parent = parent;
		initLayout();
	}

	private void initLayout() {
		Object[] messages = new Object[1];
		{
			JPanel srp = new JPanel();
			srp.setLayout(new BorderLayout());
			JLabel jl = new JLabel("Are you sure you want to close this tab?");
			srp.add(jl, BorderLayout.NORTH);
			messages[0] = srp;
		}

		pane = new JOptionPane(messages, // the dialog message array
				JOptionPane.QUESTION_MESSAGE, // message type
				JOptionPane.DEFAULT_OPTION, // option type
				null, // optional icon, use null to use the default icon
				OPTIONS, // options string array, will be made into buttons
				OPTIONS[0]);

		dialog = pane.createDialog(parent,  LangTool.getString("sa.confirmTabClose"));

	}

	/**
	 * Shows the dialog and returns the true if the close was confirmed
	 * or false if the operation was canceled.
	 *
	 * @return flag
	 */
	public boolean show() {
		dialog.setVisible(true);
		if (OPTIONS[0].equals(pane.getValue())) {
			return true;
		}
		return false;
	}

}
