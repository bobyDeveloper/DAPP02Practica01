package BDNC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD instance;
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "123";

    private ConexionBD() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static ConexionBD getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexionBD();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConexionBD();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}