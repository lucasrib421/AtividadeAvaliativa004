package com.mycompany.atividadeavaliativaiv;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface IDao<E> {

    String getSaveStatement();
    String getUpdateStatement();
    String getFindByIdStatement();
    String getFindAllStatement();
    String getDeleteStatement();

    void composeSaveOrUpdateStatement(PreparedStatement pstmt, E e);

    E extractObject(ResultSet rs);

    List<E> extractObjects(ResultSet rs);

    Long saveOrUpdate(E e);

    E findById(Long id);

    List<E> findAll();

    void delete(Long id);
}
