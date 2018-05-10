package model.service.impl;

import model.dao.AbstractDao;
import model.dao.RoleDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.Role;
import model.entity.User;
import model.exception.IncorrectDataForUserException;
import model.exception.LoginAlreadyExistsException;
import model.exception.LoginNotFoundException;
import model.service.UserService;
import org.apache.log4j.Logger;
import util.ConnectionUtil;
import view.ExceptionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static view.ExceptionMessage.*;
import static view.constant.general.Parameters.*;
import static view.constant.general.Global.*;


public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(AbstractDao.class);
    private UserServiceImpl() {
    }

    private static final class UserServiceImplHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.instance;
    }

    @Override
    public User findByEmail(String email) throws LoginNotFoundException {
        User user = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            user = userDao.findByEmail(email);
            if (user == null) {
                throw new LoginNotFoundException(email, ExceptionMessage.EMAIL_NOT_FOUND_ERROR);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // LOG
        }
        return user;
    }

    @Override
    public User register(User user) throws LoginAlreadyExistsException, IncorrectDataForUserException {
        validation(user); // exception business logic driven is not the best way, but in that case can be
        User savedUser = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource(); // is any exception possible here?
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            RoleDao roleDao = daoFactory.createRoleDao();
            Role role = roleDao.findByName(USER_ROLE);
            savedUser = userDao.save(user);
            userDao.setUserRole(savedUser.getId(), role.getId()); // why setting of user role is the separate action?
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            ConnectionUtil.rollback(connection);
            LOG.error("DAO: exception = ", ex);
            throw new LoginAlreadyExistsException(ExceptionMessage.EMAIL_EXIST_ERROR);
        } catch (SQLException ex) {
            ConnectionUtil.rollback(connection);
            ex.printStackTrace(); // use LOG
        } finally {
            ConnectionUtil.close(connection);
        }
        return savedUser;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute(X_AUTH_TOKEN);
        User user = null; // move into try {}
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            user = userDao.findOne(id);
            LOG.debug("DAO: User was found"); // add info about user
        } catch (SQLException ex) {
            ex.printStackTrace(); // LOG.warn() || LOG.error()
        }
        return user; // move into try {}
    }

    // why it is public but not in interface? -> change to protected at least?
    public void validation(User user) throws IncorrectDataForUserException {
        if (!isEmailValid(user.getEmail())) {
            throw new IncorrectDataForUserException(EMAIL_PATTERN_ERROR);
        }
        if (!isPasswordValid(user.getPassword())) {
            throw new IncorrectDataForUserException(PASSWORD_PATTERN_ERROR);
        }
    }

    // move to util class
    // add unit tests
    boolean isEmailValid(String email) {
        final String regex = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`\\{|\\}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    // move to util class
    // add unit tests
    boolean isPasswordValid(String password) {
        if (password.length() < 5 || password.length() > 30)
            return false;
        final String regex = "^[a-zA-Z0-9!@#$%^&*()_+|~\\-=\\/‘\\{\\}\\[\\]:\";’<>?,./]+$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }
}
