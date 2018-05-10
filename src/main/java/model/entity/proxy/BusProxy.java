package model.entity.proxy;

import model.dao.RouteDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.Bus;
import model.entity.Route;
import model.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BusProxy extends Bus {

    @Override
    public List<User> getUsers() {
        if (super.getUsers() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                UserDao userDao = daoFactory.createUserDao();
                return userDao.findByBus(getId());
            } catch (SQLException e) {
                e.printStackTrace(); // add some logger (e.g. log4j) you should no have any printStackTrace() in code
            }
            return super.getUsers(); // NullPointerException will be thrown when that code will be reached
        }
        return super.getUsers();
    }
    @Override
    public List<Route> getRoutes() {
        if (super.getRoutes() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                RouteDao busDao = daoFactory.createRouteDao();
                return busDao.findByBus(getId());
            } catch (SQLException e) {
                e.printStackTrace(); // the same as above
            }
            return super.getRoutes(); // the same as above
        }
        return super.getRoutes();
    }
}
