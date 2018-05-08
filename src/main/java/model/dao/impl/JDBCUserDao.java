package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.UserDao;
import model.dao.util.QueryBuilder;
import model.entity.User;
import model.entity.proxy.UserProxy;

import java.sql.*;
import java.util.List;

import static view.constant.table.UserConstants.*;

public class JDBCUserDao extends AbstractDao<User> implements UserDao {
    private JDBCUserDao(Connection connection) {
        super(TABLE_USER, connection);
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE_USER)
                .where()
                .condition(TABLE_USER, EMAIL)
                .build();
        return getEntityByQuery(query, email);
    }

    @Override
    public void setUserRole(Long userId, Long roleId) {
        String query = new QueryBuilder()
                .insert()
                .into()
                .table(USER_HAS_ROLE_TABLE)
                .insertValues(new String[]{USER_COLUMN, ROLE_COLUMN})
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findByRole(Long roleId) throws SQLException {
        String query = "SELECT u.* FROM `user_has_role` uhr \n" +
                "inner join `user` u ON uhr.id_user = u.id\n" +
                "where uhr.id_role = ?";
        return getEntityListByQuery(query, roleId);
    }

    @Override
    public List<User> findByBus(Long busId) throws SQLException {
        String query = "SELECT u.* FROM `driver_has_bus` dhb \n" +
                "inner join `user` u ON dhb.id_user = u.id\n" +
                "where uhr.id_bus = ?";
        return getEntityListByQuery(query, busId);
    }

    private static class JDBCUserDaoHolder {
        private static JDBCUserDao instance(Connection connection) {
            return new JDBCUserDao(connection);
        }
    }

    public static JDBCUserDao getInstance(Connection connection) {
        return JDBCUserDaoHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{FIRST_NAME, LAST_NAME, EMAIL, PASSWORD};
    }

    @Override
    protected void setEntityParameters(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        return new UserProxy.UserBuilder()
                .setId(id)
                .setEmail(email)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .buildUserProxy();
    }

}
