/**
 * Clase: TN5250jFileChooser.java
 * Descripción: Extensión de JFileChooser que corrige el cálculo de tamaño preferido y aplica traducciones
 * personalizadas a los textos del selector de archivos para idiomas no soportados por defecto.
 */
package com.ide.as400.gui;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import com.ide.as400.tools.LangTool;

import java.awt.Dimension;

public class TN5250jFileChooser extends JFileChooser {

   private static final long serialVersionUID = 1L;

   static {
      doTranslation();
   }

	public TN5250jFileChooser(String dir) {
		super(dir);
	}

	/**
	 * This is to fix
	 * Bug Id - 4416982
	 * Synopsis JFileChooser does not use its resources to size itself initially
	 **/

	public Dimension getPreferredSize() {
		return getLayout().preferredLayoutSize(this);
	}

	/* This method is included because Sun does not supports translations
	 * for various languages at this time, for example dutch and russian
	 * are not included yet. So until Sun fixes this we need to use this
	 * self-made method (doTranslation) to translate the JFileChoosers.
	 */

	static void doTranslation() {
		UIManager.put("FileChooser.lookInLabelText",
			LangTool.getString("jfc.Lookin") + ":");
		UIManager.put("FileChooser.upFolderToolTipText",
			LangTool.getString("jfc.UpOneLevel"));
		UIManager.put("FileChooser.newFolderToolTipText",
			LangTool.getString("jfc.CreateNewFolder"));
		UIManager.put("FileChooser.listViewButtonToolTipText",
			LangTool.getString("jfc.List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText",
			LangTool.getString("jfc.Details"));
		UIManager.put("FileChooser.fileNameLabelText",
			LangTool.getString("jfc.FileName") + ":");
		UIManager.put("FileChooser.filesOfTypeLabelText",
			LangTool.getString("jfc.FilesOfType")+ ":");
		UIManager.put("FileChooser.openButtonText",
			LangTool.getString("jfc.Open"));
		UIManager.put("FileChooser.openButtonToolTipText",
			LangTool.getString("jfc.OpenSelectedFile"));
		UIManager.put("FileChooser.cancelButtonText",
			LangTool.getString("jfc.Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText",
			LangTool.getString("jfc.Cancel"));
		UIManager.put("FileChooser.saveInLabelText",
			LangTool.getString("jfc.Savein") + ":");
		UIManager.put("FileChooser.saveButtonText",
			LangTool.getString("jfc.Save"));
		UIManager.put("FileChooser.saveButtonToolTipText",
			LangTool.getString("jfc.SaveSelectedFile"));
	}
}
