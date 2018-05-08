package model.dao;

import model.entity.Route;

import java.sql.SQLException;
import java.util.List;

public interface RouteDao extends GenericDao<Route, Long> {
    Route findByName(String value) throws SQLException;

    List<Route> findByBus(Long busId) throws SQLException;
}
