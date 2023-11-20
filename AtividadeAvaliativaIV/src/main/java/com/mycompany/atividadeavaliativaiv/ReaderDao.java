package com.mycompany.atividadeavaliativaiv;

import com.mycompany.pooproject3.Credential;
import com.mycompany.pooproject3.Reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderDao extends Dao<Reader> {

    public static final String TABLE = "reader";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(id) values (?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set  where id = ?";
    }

    @Override
    public Long saveOrUpdate(Reader e) {

        Long readerId = new UserDao().saveOrUpdate(e);

        if (e.getId() == -1 || e.getId() == 0)
            e.setId(-readerId);
        else
            e.setId(readerId);

        super.saveOrUpdate(e);
        new CredentialDao().saveOrUpdate(e.getCredential());

        return readerId;
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Reader e) {
        try {
            if (e.getId() != -1 && e.getId() < 0) {
                pstmt.setLong(1, -e.getId());
            } else {
                pstmt.setLong(1, e.getId());
            }
        } catch (SQLException ex) {
            System.out.println(">> " + ex);
        }
    }

    @Override
    public String getFindByIdStatement() {
        return "select id from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select id from " + TABLE;
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public Reader extractObject(ResultSet rs) {

        Reader reader = null;

        try {

            reader = new Reader();

            reader.setId(rs.getLong("id"));
            Credential credential = new CredentialDao().findById(reader.getId());
            reader.setName(credential.getUser().getName());
            reader.setEmail(credential.getUser().getEmail());
            reader.setRole(credential.getUser().getRole());
            reader.setBirthdate(credential.getUser().getBirthdate());
            reader.setCredential(credential);
        } catch (Exception ex) {
            System.out.println(">> " + ex);
        }

        return reader;
    }
}
