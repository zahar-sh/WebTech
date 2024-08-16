package by.bsuir.app.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties {
    private static final DBProperties INSTANCE = new DBProperties();
    private final Properties properties;

    private DBProperties() {
        try {
            Class<? extends DBProperties> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found" + e.getMessage(), e);
        }
    }

    public static DBProperties getInstance() {
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}
