package model.service;

import model.dao.impl.JDBCUserDao;
import model.service.impl.AdminServiceImpl;
import model.service.impl.UserServiceImpl;

public class ServiceFactory {
    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        private static ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }


    public UserService createUserService() {
        return UserServiceImpl.getInstance();
    }

    public AdminService createAdminService(){
        return AdminServiceImpl.getInstance();
    }

}
