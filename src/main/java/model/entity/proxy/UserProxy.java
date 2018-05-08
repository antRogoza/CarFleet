package model.entity.proxy;

import model.dao.BusDao;
import model.dao.ConfirmationDao;
import model.dao.RoleDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.Bus;
import model.entity.Confirmation;
import model.entity.Role;
import model.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserProxy extends User {
    @Override
    public List<Role> getRoles() {
        if (super.getRoles() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                RoleDao roleDao = daoFactory.createRoleDao();
                return roleDao.findByUser(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getRoles();
    }
    @Override
    public List<Bus> getBuses(){
        if (super.getBuses() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                BusDao busDao = daoFactory.createBusDao();
                return busDao.findByUser(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getBuses();
    }

}
