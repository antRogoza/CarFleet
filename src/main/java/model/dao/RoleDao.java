package model.dao;

import model.entity.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao extends GenericDao<Role,Long>{
    Role findByName(String value) throws SQLException;

    List<Role> findByUser(Long userId) throws SQLException;
}
