package model.entity.proxy;

import model.dao.BusDao;
import model.dao.RouteDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.Bus;
import model.entity.Route;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RouteProxy extends Route {
    @Override
    public List<Bus> getBuses() {
        if (super.getBuses() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                BusDao busDao = daoFactory.createBusDao();
                return busDao.findByRoute(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return super.getBuses();
        }
        return super.getBuses();
    }

}
