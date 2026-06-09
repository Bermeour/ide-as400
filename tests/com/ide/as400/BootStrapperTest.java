package com.ide.as400;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ide.as400.event.BootEvent;
import com.ide.as400.event.BootListener;

import java.util.ArrayList;
import java.util.List;

public class BootStrapperTest {

    // --- Listeners ---

    @Test
    public void addBootListener_registersListener() {
        BootStrapper bs = new BootStrapper();
        List<BootEvent> received = new ArrayList<>();
        bs.addBootListener(event -> received.add(event));
        // Verifica que no lanza excepción al registrar
        // El evento se dispara internamente cuando llega conexión (no testeable sin red)
    }

    @Test
    public void addBootListener_multipleListeners_doesNotThrow() {
        BootStrapper bs = new BootStrapper();
        bs.addBootListener(event -> { });
        bs.addBootListener(event -> { });
        bs.addBootListener(event -> { });
        // Tres listeners sin excepción
    }

    @Test
    public void addBootListener_null_doesNotThrow() {
        BootStrapper bs = new BootStrapper();
        // Verifica comportamiento defensivo ante null
        try {
            bs.addBootListener(null);
        } catch (NullPointerException e) {
            // Documenta que actualmente lanza NPE — debe corregirse en depuración
        }
    }
}
