package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.BusDao;
import model.dao.util.QueryBuilder;
import model.entity.Bus;
import model.entity.Role;
import model.entity.proxy.BusProxy;
import model.entity.proxy.RoleProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static view.constant.table.BusConstants.*;

public class JDBCBusDao extends AbstractDao<Bus> implements BusDao{
    private JDBCBusDao(Connection connection) {
        super(TABLE_BUS, connection);
    }

    private static final class JDBCBusDaoHolder {
        private static JDBCBusDao instance(Connection connection) {
            return new JDBCBusDao(connection);
        }
    }

    public static JDBCBusDao getInstance(Connection connection) {
        return JDBCBusDao.JDBCBusDaoHolder.instance(connection);
    }

    @Override
    public Bus findByName(String value) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE_BUS)
                .where()
                .condition(TABLE_BUS, BRAND)
                .build();
        return getEntityByQuery(query, value);
    }

    @Override
    public List<Bus> findByUser(Long userId) throws SQLException {
        String query = "SELECT b.* FROM `driver_has_bus` dhb " +
                "inner join `bus` b ON dhb.id_bus = b.id " +
                "where dhb.id_user = ?";
        return getEntityListByQuery(query, userId);
    }

    @Override
    public List<Bus> findByRoute(Long routeId) throws SQLException{
        String query = "SELECT b.* FROM `bus_has_route` dhr " +
                "inner join `bus` b ON dhr.id_bus = b.id " +
                "where dhr.id_route = ?";
        return getEntityListByQuery(query, routeId);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{BRAND,NUMBER_OF_SEATS};
    }

    @Override
    protected void setEntityParameters(Bus entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getBrand());
        statement.setString(2,String.valueOf(entity.getNumberOfSeats()));
    }

    @Override
    protected Bus getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String brand = resultSet.getString(BRAND);
        int numberOfSeats = resultSet.getInt(NUMBER_OF_SEATS);
        return new BusProxy.BusBuilder()
                .setId(id)
                .setBrand(brand)
                .setNumberOfSeats(numberOfSeats)
                .buildBusProxy();
    }
}
