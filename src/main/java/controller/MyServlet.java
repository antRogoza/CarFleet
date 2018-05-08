package controller;

import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            List<User> userList = userDao.findAll();
            System.out.println(userList);
            connection.commit();
        } catch (SQLException e) {
            //ConnectionUtil.rollback(connection);
            e.printStackTrace();
        } finally {
           //ConnectionUtil.close(connection);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
