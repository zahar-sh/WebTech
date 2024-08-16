package by.bsuir.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final ConnectionCreator INSTANCE = new ConnectionCreator();

    private final String url;
    private final String name;
    private final String password;

    private ConnectionCreator() {
        try {
            Properties properties = DBProperties.getInstance().getProperties();

            url = properties.getProperty("db.url");
            name = properties.getProperty("db.name");
            password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Driver is not found" + e.getMessage(), e);
        }
    }

    public static ConnectionCreator getInstance() {
        return INSTANCE;
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
