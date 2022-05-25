package ro.unibuc.etickets.database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection;

    public DbConnection() {

    }

    public static Connection getInstance() throws SQLException {
        if(connection == null) {
            String url = "jdbc:mysql://localhost:3306/E_Ticketing_Platform";
            String user = "root";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
