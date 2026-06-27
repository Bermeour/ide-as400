/**
 * Clase: Tn5250jKeyEvents.java
 * Descripción: Evento especializado de TN5250j que extiende Tn5250jEvent para transportar
 * la secuencia de pulsaciones de teclado producidas durante una sesión de terminal 5250.
 */
package com.ide.as400.framework;

//import com.ide.as400.Screen5250;
import com.ide.as400.framework.tn5250.Screen5250;

public class Tn5250jKeyEvents extends Tn5250jEvent {
	private String keystrokes;

	public Tn5250jKeyEvents(Screen5250 screen, String strokes) {
		super(screen);
		this.keystrokes = strokes;
	}

	public String getKeystrokes() {
		return this.keystrokes;
	}

}
