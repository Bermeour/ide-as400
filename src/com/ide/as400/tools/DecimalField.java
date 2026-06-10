/**
 * Clase: DecimalField.java
 * Descripción: Campo de texto Swing especializado que valida y formatea la entrada del usuario
 * para aceptar únicamente valores numéricos decimales según un formato especificado.
 */
package com.ide.as400.tools;

import javax.swing.*;

import java.awt.Toolkit;
import java.text.*;

public class DecimalField extends JTextField {
    private static final long serialVersionUID = 1L;
	private NumberFormat format;

    public DecimalField(double value, int columns, NumberFormat f) {
        super(columns);
        setDocument(new FormattedDocument(f));
        format = f;
        setValue(value);
    }

    public double getValue() {
        double retVal = 0.0;

        try {
            retVal = format.parse(getText()).doubleValue();
        } catch (ParseException e) {
            // This should never happen because insertString allows
            // only properly formatted data to get in the field.
            Toolkit.getDefaultToolkit().beep();
            System.err.println("getValue: could not parse: " + getText());
        }
        return retVal;
    }

    public void setValue(double value) {
        setText(format.format(value));
    }
}
