import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DataSourceFactory;
import model.entity.User;
import view.View;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args){
//        View view = new View();
//        view.printMessage(View.bundle.getString(View.AUTHOR));

//        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
//            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
//            UserDao userDao = daoFactory.createUserDao();
//            List<User> userList= userDao.findAll();
//            System.out.println(userList);
//            connection.commit();
//        } catch (SQLException e) {
//            //ConnectionUtil.rollback(connection);
//            e.printStackTrace();
//        } finally {
//           //ConnectionUtil.close(connection);
//        }
    }
}
