package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.RoleDao;
import model.dao.util.QueryBuilder;
import model.entity.Role;
import model.entity.proxy.RoleProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static view.constant.table.RoleConstants.*;

public class JDBCRoleDao extends AbstractDao<Role> implements RoleDao {
    private JDBCRoleDao(Connection connection) {
        super(TABLE_ROLE, connection);
    }

    private static final class JDBCRoleDaoHolder {
        private static JDBCRoleDao instance(Connection connection) {
            return new JDBCRoleDao(connection);
        }
    }

    public static JDBCRoleDao getInstance(Connection connection) {
        return JDBCRoleDaoHolder.instance(connection);
    }

    @Override
    public Role findByName(String value) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE_ROLE)
                .where()
                .condition(TABLE_ROLE, NAME)
                .build();
        return getEntityByQuery(query, value);
    }

    @Override
    public List<Role> findByUser(Long userId) throws SQLException {
        String query = "SELECT r.* FROM `user_has_role` uhr " +
                "inner join `role` r ON uhr.id_role = r.id " +
                "where uhr.id_user = ?";
        return getEntityListByQuery(query, userId);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(Role entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
    }

    @Override
    protected Role getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new RoleProxy.RoleBuilder()
                .setId(id)
                .setName(name)
                .buildRoleProxy();
    }
}
