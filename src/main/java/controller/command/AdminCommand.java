package controller.command;

import model.service.AdminService;
import view.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements  Command{
    private static final String EXCEPTION = "exception";
    private static final String PAGE = "page";
    private static final int NUMBER_OF_ORDERS_IN_PAGE = 4;

    private final AdminService adminService;

    public AdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(EXCEPTION);
        return Pages.ADMIN;
    }
}
