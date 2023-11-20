package com.mycompany.atividadeavaliativaiv;

import com.mycompany.pooproject3.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<E> implements IDao<E> {

    public static final String DATABASE_NAME = "alphasystem";

    @Override
    public List<E> extractObjects(ResultSet resultSet) {
        List<E> list = new ArrayList<>();

        return list;
    }

    @Override
    public Long saveOrUpdate(E entity) {
        Long id = 0L;

        if (((Entity) entity).getId() == -1 || ((Entity) entity).getId() == 0) {
            try (PreparedStatement preparedStatement = ConnectionDao.connectToDatabase().prepareStatement(getSaveStatement(), Statement.RETURN_GENERATED_KEYS)) {
                composeSaveOrUpdateStatement(preparedStatement, entity);

                System.out.println(">> SQL: " + preparedStatement);

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }

            } catch (Exception ex) {
                System.out.println(">> " + ex);
            }
        } else {
            try (PreparedStatement preparedStatement = ConnectionDao.connectToDatabase().prepareStatement(getUpdateStatement())) {
                composeSaveOrUpdateStatement(preparedStatement, entity);

                System.out.println(">> SQL: " + preparedStatement);

                preparedStatement.executeUpdate();

                id = ((Entity) entity).getId();

            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }
        }
        return id;
    }

    @Override
    public E findById(Long id) {
        try (PreparedStatement preparedStatement = ConnectionDao.connectToDatabase().prepareStatement(getFindByIdStatement())) {
            preparedStatement.setLong(1, id);

            System.out.println(">> SQL: " + preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractObject(resultSet);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public List<E> findAll() {
        try (PreparedStatement preparedStatement = ConnectionDao.connectToDatabase().prepareStatement(getFindAllStatement())) {

            System.out.println(">> SQL: " + preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            return extractObjects(resultSet);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = ConnectionDao.connectToDatabase().prepareStatement(getDeleteStatement())) {
            preparedStatement.setLong(1, id);

            System.out.println(">> SQL: " + preparedStatement);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }

}
