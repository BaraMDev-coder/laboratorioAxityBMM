package com.axity.dinosaurpark.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ParkConfig {

    private static ParkConfig instance;
    private final Properties props;

    // Constructor PRIVADO — nadie puede hacer "new ParkConfig()"
    private ParkConfig() {
        props = new Properties();
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("park.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar park.properties: " + e.getMessage());
        }
    }

    // Punto de acceso global — crea la instancia solo si no existe
    public static ParkConfig getInstance() {
        if (instance == null) {
            instance = new ParkConfig();
        }
        return instance;
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public double getDouble(String key, double defaultValue) {
        return Double.parseDouble(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public long getSeed() {
        return Long.parseLong(props.getProperty("simulation.seed", "42"));
    }

    public int getTotalSteps() {
        return Integer.parseInt(props.getProperty("simulation.totalSteps", "100"));
    }

    // Solo para tests — permite resetear la instancia entre tests
    static void resetForTesting() { instance = null; }
}