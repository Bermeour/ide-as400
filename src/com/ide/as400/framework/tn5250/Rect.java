/**
 * Clase: Rect.java
 * Descripción: Clase auxiliar que representa un rectángulo mediante coordenadas (x, y) y
 * dimensiones (ancho, alto), diseñada como alternativa ligera a java.awt.Rectangle para
 * evitar dependencias innecesarias con el paquete AWT en el núcleo del protocolo.
 */
package com.ide.as400.framework.tn5250;


public class Rect {

	/* default */ int x;
	/* default */ int y;
	/* default */ int height;
	/* default */ int width;

	/**
	 * @param rect a rect angle
	 */
	public void setBounds(Rect rect) {
		setBounds(rect.x, rect.y, rect.width, rect.height);
	}

    /**
     * @param x the new X coordinate for the upper-left corner of this rectangle
     * @param y the new Y coordinate for the upper-left corner of this rectangle
     * @param width the new width for this rectangle
     * @param height the new height for this rectangle
     */
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

}
