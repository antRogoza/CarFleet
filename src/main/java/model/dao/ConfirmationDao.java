package model.dao;

import model.entity.Confirmation;

import java.sql.SQLException;
import java.util.List;

public interface ConfirmationDao extends GenericDao<Confirmation,Long>{
    Confirmation findByName(String value) throws SQLException;

    List<Confirmation> findByUser(Long userId) throws SQLException;
}
