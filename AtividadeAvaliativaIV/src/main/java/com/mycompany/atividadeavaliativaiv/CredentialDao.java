package com.mycompany.atividadeavaliativaiv;

import com.mycompany.pooproject3.Credential;
import com.mycompany.pooproject3.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CredentialDao extends Dao<Credential> {

    public static final String TABLE = "credential";
    private static String SALT = "!asdf";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(id, username, password, lastAcess, enabled) values(?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set username = ?, password = ?, lastAcess = ?, enabled = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, username, password, lastAcess, enabled" + " from credential where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, username, password, lastAcess, enabled" + " from credential";
    }

    @Override
    public String getDeleteStatement() {
        return "Delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Credential e) {
        try {

            if (e.getId() < 0) {
                pstmt.setLong(1, -e.getUser().getId());
                pstmt.setString(2, e.getUsername());
                pstmt.setString(3, crypPass(e.getPassword()));
                pstmt.setObject(4, e.getLastAcess(), Types.DATE);
                pstmt.setObject(5, e.isEnabled());
            } else {
                pstmt.setLong(1, e.getUser().getId());
                pstmt.setString(2, e.getUsername());
                pstmt.setString(3, e.getPassword());
                pstmt.setObject(4, e.getLastAcess(), Types.DATE);
                pstmt.setObject(5, e.isEnabled());
            }

        } catch (SQLException ex) {
            Logger.getLogger(CredentialDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Credential extractObject(ResultSet resultSet) {

        Credential credential = null;

        try {
            credential = new Credential();
            credential.getUser().setId(resultSet.getLong("id"));
            credential.setUsername(resultSet.getString("username"));
            credential.setPassword(resultSet.getString("password"));
            credential.setLastAcess(resultSet.getObject("lastAcces", LocalDate.class));
            credential.setEnabled(resultSet.getObject("enabled", Boolean.class));

        } catch (Exception ex) {
            Logger.getLogger(CredentialDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return credential;
    }

    public User authenticate(Credential credential) {

        UserDao userDao = new UserDao();

        for (User user : userDao.findAll()) {

            if (credential != null && credential.getUsername().equals(user.getCredential().getUsername())) {

                String hashedPassword = crypPass(credential.getPassword());

                if (hashedPassword.equals(user.getCredential().getPassword())) {
                    return user;
                }
            }
        }

        return null;
    }

    public String crypPass(String password) {

        String saltedPassword = password + SALT;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = md.digest(saltedPassword.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criar hash MD5", e);
        }
    }
}
