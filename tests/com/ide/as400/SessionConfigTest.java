package com.ide.as400;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.ide.as400.event.SessionConfigEvent;
import com.ide.as400.event.SessionConfigListener;
import com.ide.as400.interfaces.ConfigureFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase: SessionConfigTest.java
 * Descripción: Prueba la gestión de propiedades y eventos de la clase SessionConfig, cubriendo
 * lectura, escritura, eliminación de propiedades y la notificación correcta a los SessionConfigListener.
 */
public class SessionConfigTest {

    @BeforeClass
    public static void setUpClass() throws IOException {
        // Necesario para que SessionConfig no intente leer archivos reales
        System.setProperty("emulator.settingsDirectory",
                File.createTempFile("tn5250j", "test").getParent() + File.separator);
        ConfigureFactory.getInstance();
    }

    private SessionConfig config;

    @Before
    public void setUp() {
        config = new SessionConfig("", "TestSession");
    }

    // --- Configuración de recurso ---

    @Test
    public void getConfigurationResource_returnsDefault_whenEmpty() {
        assertEquals("Con recurso vacío debe usar TN5250JDefaults.props",
                "TN5250JDefaults.props", config.getConfigurationResource());
    }

    @Test
    public void getSessionName_returnsCorrectName() {
        assertEquals("TestSession", config.getSessionName());
    }

    // --- Propiedades ---

    @Test
    public void setProperty_isPropertyExists_returnsTrue() {
        config.setProperty("testKey", "testValue");
        assertTrue(config.isPropertyExists("testKey"));
    }

    @Test
    public void isPropertyExists_returnsFalse_forMissingKey() {
        assertFalse(config.isPropertyExists("keyQueNoExiste"));
    }

    @Test
    public void getStringProperty_returnsValue_afterSetProperty() {
        config.setProperty("color", "blue");
        assertEquals("blue", config.getStringProperty("color"));
    }

    @Test
    public void getStringProperty_returnsEmpty_forMissingKey() {
        assertEquals("", config.getStringProperty("keyQueNoExiste"));
    }

    @Test
    public void getIntegerProperty_returnsCorrectValue() {
        config.setProperty("port", "23");
        assertEquals(23, config.getIntegerProperty("port"));
    }

    @Test
    public void getIntegerProperty_returns0_forInvalidNumber() {
        config.setProperty("port", "noEsNumero");
        assertEquals(0, config.getIntegerProperty("port"));
    }

    @Test
    public void getIntegerProperty_returns0_forMissingKey() {
        assertEquals(0, config.getIntegerProperty("keyQueNoExiste"));
    }

    @Test
    public void removeProperty_removesKey() {
        config.setProperty("temp", "valor");
        assertTrue(config.isPropertyExists("temp"));
        config.removeProperty("temp");
        assertFalse(config.isPropertyExists("temp"));
    }

    // --- setModified ---

    @Test
    public void setModified_marksSaveMe() {
        config.setModified();
        assertTrue("setModified debe poner la clave 'saveme'",
                config.isPropertyExists("saveme"));
    }

    // --- Listeners ---

    @Test
    public void addSessionConfigListener_firesOnPropertyChange() {
        List<SessionConfigEvent> received = new ArrayList<>();

        config.addSessionConfigListener(received::add);
        config.firePropertyChange(this, "font", "Arial", "Courier");

        assertEquals("Debe recibir 1 evento", 1, received.size());
        assertEquals("font", received.get(0).getPropertyName());
        assertEquals("Arial", received.get(0).getOldValue());
        assertEquals("Courier", received.get(0).getNewValue());
    }

    @Test
    public void firePropertyChange_doesNotFire_whenValuesAreEqual() {
        List<SessionConfigEvent> received = new ArrayList<>();

        config.addSessionConfigListener(received::add);
        config.firePropertyChange(this, "font", "Arial", "Arial");

        assertEquals("No debe disparar evento cuando los valores son iguales", 0, received.size());
    }

    @Test
    public void removeSessionConfigListener_stopsReceivingEvents() {
        List<SessionConfigEvent> received = new ArrayList<>();
        SessionConfigListener listener = received::add;

        config.addSessionConfigListener(listener);
        config.removeSessionConfigListener(listener);
        config.firePropertyChange(this, "font", "Arial", "Courier");

        assertEquals("No debe recibir eventos tras ser eliminado", 0, received.size());
    }

    @Test
    public void firePropertyChange_doesNotThrow_whenNoListeners() {
        // Sin listeners, no debe lanzar excepción
        config.firePropertyChange(this, "font", "Arial", "Courier");
    }

    // --- Keypad ---

    @Test
    public void getConfig_notNull() {
        assertNotNull(config.getConfig());
    }

    @Test
    public void getConfig_keypadFontSize_defaultValue() {
        assertEquals(SessionConfig.KEYPAD_FONT_SIZE_DEFAULT_VALUE,
                config.getConfig().getKeypadFontSize(), 0.001f);
    }
}
