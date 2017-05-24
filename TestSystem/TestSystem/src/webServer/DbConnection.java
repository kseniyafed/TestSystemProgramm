package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    private final static DbConnection inst = new DbConnection();
    Connection connection;

    public DbConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:DbTestSystem.db");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnection instance() {
        return inst;
    }

    public Connection getConnection() {
        return connection;
    }
}