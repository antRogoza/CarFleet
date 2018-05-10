package model.entity.proxy;

import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.Role;
import model.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleProxy extends Role {
    @Override
    public List<User> getUsers() {
        if (super.getUsers() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                UserDao userDao = daoFactory.createUserDao();
                return userDao.findByRole(getId());
            } catch (SQLException e) {
                e.printStackTrace(); // see BusProxy
            }
            return super.getUsers(); // see BusProxy
        }
        return super.getUsers();
    }
}
