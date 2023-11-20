package com.mycompany.atividadeavaliativaiv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionDao {

    public static Connection connectToDatabase() {
        Connection conn = null;

        String dbName = "alphasystem";

        try {
            String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=root&password=";
            conn = DriverManager.getConnection(url);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "DAO/ConnectionDAO: " + erro.getMessage());
        }
        return conn;
    }
}
