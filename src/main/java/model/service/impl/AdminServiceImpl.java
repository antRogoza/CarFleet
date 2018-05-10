package model.service.impl;

import model.service.AdminService;

public class AdminServiceImpl implements AdminService { // why do you implements interface but still uses static methods?
    private AdminServiceImpl() {
    }

    private static final class AdminServiceImplHolder {
        private static final AdminServiceImpl instance = new AdminServiceImpl();
    }

    public static AdminServiceImpl getInstance() {
        return AdminServiceImpl.AdminServiceImplHolder.instance;
    }
}
