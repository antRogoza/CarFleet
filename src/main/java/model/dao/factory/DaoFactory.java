package model.dao.factory;

import model.dao.*;
import model.entity.Confirmation;

import java.sql.Connection;

public abstract class DaoFactory {

    public abstract UserDao createUserDao();

    public abstract RoleDao createRoleDao();

    public abstract RouteDao createRouteDao();

    public abstract BusDao createBusDao();

    public abstract ConfirmationDao createConfirmationDao();

    public static DaoFactory getDaoFactory(Connection connection) {
            return SQLDaoFactory.getInstance(connection);
        }
}

