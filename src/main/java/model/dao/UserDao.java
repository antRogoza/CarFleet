package model.dao;

import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends GenericDao<User,Long> {
    User findByEmail(String email) throws SQLException;

    void setUserRole(Long userId, Long roleId);

    List<User> findByRole(Long id) throws SQLException;

    List<User> findByBus(Long id) throws SQLException;
}
