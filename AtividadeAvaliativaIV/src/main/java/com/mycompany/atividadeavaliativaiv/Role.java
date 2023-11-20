package com.mycompany.atividadeavaliativaiv;

import com.mycompany.pooproject3.dao.ConnectionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

public class Role extends Entity {

    private String name;
    private List<User> users;

    public Role() {}

    public Role(String name) {
        this.name = name;
        createRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.length() > 20) {
            throw new Exception("O nome nao pode ter mais que 150 caracteres/nÃ£o pode ser nulo.");
        }
        this.name = name;
    }

    private void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = new ConnectionDao().connectToDatabase();
            stmt = conn.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + name + " (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(255)" +
                    ")";
            stmt.executeUpdate(createTableSQL);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar: " + erro.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }

    private void createRole() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionDao().connectToDatabase();

            String checkIfExistsSQL = "SELECT id FROM role WHERE name = ?";
            pstmt = conn.prepareStatement(checkIfExistsSQL);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                long existingId = rs.getLong("id");
                setId(existingId);
            } else {
                String insertSQL = "INSERT INTO role (name) VALUES (?)";
                pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, name);
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    setId(generatedId);
                }
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro" + e.getMessage());
            }
        }
    }
}
