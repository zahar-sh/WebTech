package by.bsuir.app.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private static final Lock connectionLock = new ReentrantLock();
    private static final Lock returnLock = new ReentrantLock();

    private final Deque<Connection> connections;
    private final Semaphore semaphore;

    private ConnectionPool() {
        int connectionSize;
        try {
            Class<? extends ConnectionPool> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            Properties property = new Properties();
            property.load(inputStream);

            connectionSize = Integer.parseInt(property.getProperty("db.connectionSize"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found" + e.getMessage(), e);
        }
        semaphore = new Semaphore(connectionSize);
        connections = new ArrayDeque<>();

        ConnectionCreator connectionCreator = ConnectionCreator.getInstance();
        for (int i = 0; i < connectionSize; i++) {
            Connection connection = connectionCreator.createConnection();
            connections.push(connection);
        }
        if (connections.isEmpty()) {
            throw new IllegalArgumentException("Connections are not created!");
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            connectionLock.lock();
            semaphore.acquire();
            return connections.pop();
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        } finally {
            connectionLock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        try {
            returnLock.lock();
            connections.push(connection);
            semaphore.release();
        } finally {
            returnLock.unlock();
        }
    }
}
