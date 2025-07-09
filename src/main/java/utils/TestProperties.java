package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private static final String PROPERTIES_FILE = "src/main/resources/testdata.properties";
    private static Properties properties = null;

    // Cargar las propiedades al iniciar la clase
    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("No se pudo cargar el archivo de propiedades: " + PROPERTIES_FILE);
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el valor de la propiedad por su clave.
     * @param key Clave de la propiedad
     * @return Valor de la propiedad o null si no existe
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}