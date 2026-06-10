package com.ide.as400.framework.tn5250;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase: Screen5250Test.java
 * Descripción: Prueba la inicialización, dimensiones, cálculo de posiciones y manejo de listeners
 * de la clase Screen5250, verificando el comportamiento correcto de la pantalla TN5250 de 24x80.
 */
public class Screen5250Test {

    private Screen5250 screen;

    @Before
    public void setUp() {
        screen = new Screen5250();
    }

    // --- Inicialización ---

    @Test
    public void constructor_initializesDefault24x80Screen() {
        assertEquals("Filas por defecto deben ser 24", 24, screen.getRows());
        assertEquals("Columnas por defecto deben ser 80", screen.getColumns(), 80);
    }

    @Test
    public void constructor_initializesScreenLength() {
        assertEquals("Longitud pantalla debe ser 24*80=1920", 1920, screen.getScreenLength());
    }

    @Test
    public void constructor_oiaNotNull() {
        assertNotNull("OIA (Operator Information Area) no debe ser null", screen.getOIA());
    }

    @Test
    public void constructor_cursorAtPosition1_1() {
        assertEquals("Fila inicial del cursor debe ser 1", 1, screen.getCurrentRow());
        assertEquals("Columna inicial del cursor debe ser 1", 1, screen.getCurrentCol());
    }

    // --- Dimensiones ---

    @Test
    public void setRowsCols_changesScreenSizeTo27x132() {
        screen.setRowsCols(27, 132);
        assertEquals(27, screen.getRows());
        assertEquals(132, screen.getColumns());
        assertEquals(27 * 132, screen.getScreenLength());
    }

    @Test
    public void getPos_calculatesCorrectPosition() {
        // getPos es 0-based: fila 0, col 0 → posición 0
        assertEquals(0, screen.getPos(0, 0));
        // fila 0, col 1 → posición 1
        assertEquals(1, screen.getPos(0, 1));
        // fila 1, col 0 → posición 80 (segunda fila)
        assertEquals(80, screen.getPos(1, 0));
        // fila 1, col 1 → posición 81
        assertEquals(81, screen.getPos(1, 1));
    }

    @Test
    public void getRow_calculatesCorrectRow() {
        // getRow es 0-based
        // posición 0 → fila 0
        assertEquals(0, screen.getRow(0));
        // posición 80 → fila 1
        assertEquals(1, screen.getRow(80));
        // posición 160 → fila 2
        assertEquals(2, screen.getRow(160));
    }

    @Test
    public void getCol_calculatesCorrectColumn() {
        // getCol es 0-based
        // posición 0 → columna 0
        assertEquals(0, screen.getCol(0));
        // posición 5 → columna 5
        assertEquals(5, screen.getCol(5));
        // posición 80 → columna 0 (inicio de fila 1)
        assertEquals(0, screen.getCol(80));
    }

    // --- Cursor ---

    @Test
    public void getCurrentPos_afterInit_isOne() {
        // getCurrentPos devuelve lastPos+1; cursor inicia en lastPos=0, entonces es 1
        assertEquals(1, screen.getCurrentPos());
    }

    @Test
    public void getCurrentRow_afterInit_isOne() {
        // getCurrentRow es 1-based
        assertEquals(1, screen.getCurrentRow());
    }

    @Test
    public void getCurrentCol_afterInit_isOne() {
        // getCurrentCol es 1-based
        assertEquals(1, screen.getCurrentCol());
    }

    // --- Flags ---

    @Test
    public void isUsingGuiInterface_defaultFalse() {
        assertFalse("guiInterface debe ser false por defecto", screen.isUsingGuiInterface());
    }

    @Test
    public void setUseGUIInterface_true_setsFlag() {
        screen.setUseGUIInterface(true);
        assertTrue(screen.isUsingGuiInterface());
    }

    @Test
    public void setUseGUIInterface_false_clearsFlag() {
        screen.setUseGUIInterface(true);
        screen.setUseGUIInterface(false);
        assertFalse(screen.isUsingGuiInterface());
    }

    @Test
    public void hsMore_defaultValue() {
        assertEquals("More...", screen.getHSMore().toString());
    }

    @Test
    public void hsBottom_defaultValue() {
        assertEquals("Bottom", screen.getHSBottom().toString());
    }

    // --- Listeners ---

    @Test
    public void addScreenListener_doesNotThrow() {
        screen.addScreenListener(new com.ide.as400.event.ScreenListener() {
            public void onScreenChanged(int inUpdate, int startRow, int startCol, int endRow, int endCol) {}
            public void onScreenSizeChanged(int rows, int cols) {}
        });
        // Si llega aquí sin excepción, la prueba pasa
    }

    @Test
    public void removeScreenListener_doesNotThrow_whenListenerNotAdded() {
        com.ide.as400.event.ScreenListener listener = new com.ide.as400.event.ScreenListener() {
            public void onScreenChanged(int inUpdate, int startRow, int startCol, int endRow, int endCol) {}
            public void onScreenSizeChanged(int rows, int cols) {}
        };
        // No debe lanzar excepción al eliminar un listener no registrado
        screen.removeScreenListener(listener);
    }
}
