package model.dao;

import java.sql.SQLException;
import java.util.List;

interface GenericDao<T,ID> {
    T save(T entity) throws SQLException;

    T update(T entity) throws SQLException;

    T findOne(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    List<T> findAll(long limit, long offset) throws SQLException;

    void delete(ID id) throws SQLException;
}
