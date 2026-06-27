/**
 * Clase: SendScreenToFile.java
 * Descripción: Exporta el contenido textual de la pantalla 5250 activa a un archivo de texto plano
 * seleccionado por el usuario mediante un diálogo de guardar.
 */
package com.ide.as400.tools;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.ide.as400.framework.tn5250.Screen5250;
import com.ide.as400.gui.TN5250jFileChooser;
import com.ide.as400.gui.TN5250jFileFilter;
import com.ide.as400.tools.logging.TN5250jLogFactory;
import com.ide.as400.tools.logging.TN5250jLogger;

public class SendScreenToFile {

	private static final TN5250jLogger LOG = TN5250jLogFactory
			.getLogger(SendScreenToFile.class);

	/**
	 * @param parent component
	 * @param screen 5250 screen object
	 */
	public static final void showDialog(Component parent, Screen5250 screen) {
		String workingDir = System.getProperty("user.dir");
		TN5250jFileChooser fileChooser = new TN5250jFileChooser(workingDir);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new TN5250jFileFilter("txt", "Text files"));

		// int ret = pcFileChooser.showSaveDialog(new JFrame());
		int ret = fileChooser.showSaveDialog(parent);

		// check to see if something was actually chosen
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			final String fname = file.getName();
			if (fname.lastIndexOf('.') < 0) {
				file = new File(file.toString() + ".txt");
			}

			StringBuffer sb = new StringBuffer();
			char[] s = screen.getScreenAsChars();
			int c = screen.getColumns();
			int l = screen.getRows() * c;
			int col = 0;
			for (int x = 0; x < l; x++, col++) {
				sb.append(s[x]);
				if (col == c) {
					sb.append('\n');
					col = 0;
				}
			}

			writeToFile(sb.toString(), file);

		}
	}

	private static final void writeToFile(String sc, File file) {

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(sc.getBytes());
			out.flush();
			out.close();

		} catch (FileNotFoundException fnfe) {
			LOG.warn("fnfe: " + fnfe.getMessage());
		} catch (IOException ioe) {
			LOG.warn("ioe: " + ioe.getMessage());
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException exc) {
					LOG.warn("ioe finally: " + exc.getMessage());
				}

		}

	}

}
