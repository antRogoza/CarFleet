package model.dao.factory;

import model.dao.*;
import model.dao.impl.*;

import java.sql.Connection;

public class SQLDaoFactory extends DaoFactory {
    private Connection connection;

    private SQLDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDao createUserDao() {
        return JDBCUserDao.getInstance(connection);
    }

    @Override
    public RoleDao createRoleDao() {
        return JDBCRoleDao.getInstance(connection);
    }

    @Override
    public RouteDao createRouteDao(){
        return JDBCRouteDao.getInstance(connection);
    }

    @Override
    public BusDao createBusDao(){
        return JDBCBusDao.getInstance(connection);
    }
    public ConfirmationDao createConfirmationDao(){
        return JDBCConfirmationDao.getInstance(connection);
    }

    private static class MySqlDaoFactoryHolder {
        private static SQLDaoFactory instance(Connection connection) {
            return new SQLDaoFactory(connection);
        }
    }

    public static SQLDaoFactory getInstance(Connection connection) {
        return MySqlDaoFactoryHolder.instance(connection);
    }
}
