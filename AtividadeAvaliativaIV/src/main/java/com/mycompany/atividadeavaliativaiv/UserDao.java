package com.mycompany.atividadeavaliativaiv;

import com.mycompany.pooproject3.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends Dao<User> {

    public static final String TABLE = "user";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(name, email, birthdate, credential_id, role_id, user_id) values(?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set name = ?, email = ?, birthdate = ?, credential_id = ?, role_id = ? where user_id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select name, email, birthdate, credential_id, role_id, user_id" + " from user where user_id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select name, email, birthdate, credential_id, role_id, user_id" + " from user";
    }

    @Override
    public String getDeleteStatement() {
        return "Delete from " + TABLE + " where user_id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, User e) {
        try {
            pstmt.setString(1, e.getName());
            pstmt.setString(2, e.getEmail());
            pstmt.setObject(3, e.getBirthdate(), Types.DATE);
            pstmt.setLong(4, e.getCredential().getId()); //credential_id
            pstmt.setLong(5, e.getRole().getId()); //role_id

            //determina o id, caso seja uma atualização
            if (e.getId() != -1) {
                pstmt.setLong(6, e.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User extractObject(ResultSet resultSet) {

        User user = null;

        try {
            user = new User();
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setBirthdate(resultSet.getObject("birthdate", LocalDate.class));
            user.getCredential().setId(resultSet.getLong("credential_id"));
            user.getRole().setId(resultSet.getLong("role_id"));
            user.setId(resultSet.getLong("user_id"));

        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

}