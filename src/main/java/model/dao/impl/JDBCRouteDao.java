package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.RouteDao;
import model.dao.util.QueryBuilder;
import model.entity.Route;
import model.entity.proxy.RouteProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static view.constant.table.RouteConstants.*;

public class JDBCRouteDao extends AbstractDao<Route> implements RouteDao{
    private JDBCRouteDao(Connection connection) {
        super(TABLE_ROUTE, connection);
    }

    private static final class JDBCRouteDaoHolder {
        private static JDBCRouteDao instance(Connection connection) {
            return new JDBCRouteDao(connection);
        }
    }

    public static JDBCRouteDao getInstance(Connection connection) {
        return JDBCRouteDaoHolder.instance(connection);
    }

    @Override
    public Route findByName(String value) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE_ROUTE)
                .where()
                .condition(TABLE_ROUTE, NAME)
                .build();
        return getEntityByQuery(query, value);
    }

    @Override
    public List<Route> findByBus(Long busId) throws SQLException {
        String query = "SELECT r.* FROM `bus_has_route` bhr " +
                "inner join `route` r ON bhr.id_route = r.id " +
                "where uhr.id_bus = ?";
        return getEntityListByQuery(query, busId);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME,START_POINT,END_POINT};
    }

    @Override
    protected void setEntityParameters(Route entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2,entity.getStartPoint());
        statement.setString(3,entity.getEndPoint());
    }

    @Override
    protected Route getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String startPoint = resultSet.getString(START_POINT);
        String endPoint = resultSet.getString(END_POINT);
        return new RouteProxy.RouteBuilder()
                .setId(id)
                .setName(name)
                .setStartPoint(startPoint)
                .setEndPoint(endPoint)
                .buildRouteProxy();
    }
}
