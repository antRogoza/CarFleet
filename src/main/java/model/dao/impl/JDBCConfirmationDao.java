package model.dao.impl;

import model.dao.AbstractDao;
import model.dao.ConfirmationDao;
import model.dao.util.QueryBuilder;
import model.entity.Confirmation;
import model.entity.proxy.ConfirmationProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static view.constant.table.ConfirmationConstants.*;

public class JDBCConfirmationDao extends AbstractDao<Confirmation> implements ConfirmationDao {
    private JDBCConfirmationDao(Connection connection) {
        super(TABLE_CONFIRMATION, connection);
    }

    private static final class JDBCConfirmationDaoHolder {
        private static JDBCConfirmationDao instance(Connection connection) {
            return new JDBCConfirmationDao(connection);
        }
    }

    public static JDBCConfirmationDao getInstance(Connection connection) {
        return JDBCConfirmationDaoHolder.instance(connection);
    }

    @Override
    public Confirmation findByName(String value) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE_CONFIRMATION)
                .where()
                .condition(TABLE_CONFIRMATION, EMAIL_CONFIRMED)
                .build();
        return getEntityByQuery(query, value);
    }

    @Override
    public List<Confirmation> findByUser(Long userId) throws SQLException {
        String query = "SELECT c.* FROM `confirmation` c where c.id_user = ?";
        return getEntityListByQuery(query, userId);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{EMAIL_CONFIRMED,ID_USER};
    }

    @Override
    protected void setEntityParameters(Confirmation entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getEmailConfirmed());
        statement.setString(1, String.valueOf(entity.getIdUser()));
    }

    @Override
    protected Confirmation getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String emailConfirmed = resultSet.getString(EMAIL_CONFIRMED);
        Long idUser = Long.parseLong(resultSet.getString(ID_USER));
        return new ConfirmationProxy.ConfirmationBuilder()
                .setId(id)
                .setEmailConfirmed(emailConfirmed)
                .setIdUser(idUser)
                .buildConfirmationProxy();
    }
}
