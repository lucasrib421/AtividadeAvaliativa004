package com.mycompany.atividadeavaliativaiv;
package com.mycompany.atividadeavaliativaiv.Librarian;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDao extends Dao<Librarian> {

    public static final String TABLE = "librarian";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(id) values (?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set  where id = ?";
    }

    @Override
    public Long saveOrUpdate(Librarian e) {

        Long librarianId = new UserDao().saveOrUpdate(e);

        if (e.getId() == -1 || e.getId() == 0)
            e.setId(-librarianId);
        else
            e.setId(librarianId);

        super.saveOrUpdate(e);
        new CredentialDao().saveOrUpdate(e.getCredential());

        return librarianId;
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Librarian e) {
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
    public Librarian extractObject(ResultSet rs) {

        Librarian librarian = null;

        try {
            librarian = new Librarian();

            librarian.setId(rs.getLong("id"));
            Credential credential = new CredentialDao().findById(librarian.getId());
            librarian.setName(credential.getUser().getName());
            librarian.setEmail(credential.getUser().getEmail());
            librarian.setRole(credential.getUser().getRole());
            librarian.setBirthdate(credential.getUser().getBirthdate());
            librarian.setCredential(credential);
        } catch (Exception ex) {
            System.out.println(">> " + ex);
        }

        return librarian;
    }
}
