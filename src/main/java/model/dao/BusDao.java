package model.dao;

import model.entity.Bus;

import java.sql.SQLException;
import java.util.List;

public interface BusDao extends GenericDao<Bus,Long>{
    Bus findByName(String value) throws SQLException;

    List<Bus> findByUser(Long userId) throws SQLException;

    List<Bus> findByRoute(Long routId) throws SQLException;
}
