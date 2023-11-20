package com.mycompany.atividadeavaliativaiv;

import com.mycompany.pooproject3.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao extends Dao<Role> {

    public static final String TABLE = "role";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + " (id, name) values (?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set name = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select id, name from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id, name from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    private String getFindByRoleStatement() {
        return "select id, name from " + TABLE + " where name like ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Role e) {
        try {
            // caso seja atualização, substitui o id
            if (e.getId() != -1) {
                pstmt.setLong(1, e.getId());
            }

            pstmt.setString(2, e.getName());

        } catch (SQLException ex) {
            System.out.println(">> " + ex);
        }
    }

    @Override
    public Role extractObject(ResultSet resultSet) {

        Role role = null;

        try {
            role = new Role();

            role.setId(resultSet.getLong("id"));
            role.setName(resultSet.getString("name"));
        } catch (Exception ex) {
            System.out.println(">> " + ex);
        }

        return role;
    }
}
