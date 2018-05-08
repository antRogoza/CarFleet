package controller.command;

import model.entity.Role;
import model.entity.User;
import model.exception.LoginNotFoundException;
import model.service.UserService;
import view.ExceptionMessage;
import view.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static view.constant.general.Parameters.*;
import static view.constant.general.Global.*;

public class LoginCommand implements Command{
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        User user = null;
        try {
            user = userService.findByEmail(email);
        } catch (LoginNotFoundException e) {
            request.getSession().setAttribute(EXCEPTION,
                    ExceptionMessage.getMessage(e.getMessage()));
            return Pages.LOGIN;
        }
        if (user == null || !user.getPassword().equals(password)) {
            request.getSession().setAttribute(EXCEPTION,
                    ExceptionMessage.getMessage(ExceptionMessage.WRONG_PASSWORD_ERROR));
            return Pages.LOGIN;
        }
        request.getSession().setAttribute(X_AUTH_TOKEN, user.getId());
        setUserRolesIntoSession(request, user);
        return Pages.INDEX;
    }

    private void setUserRolesIntoSession(HttpServletRequest request, User user) {
        for (Role role : user.getRoles()) {
            if (ADMIN_ROLE.equals(role.getName())) {
                request.getSession().setAttribute(ADMIN, true);
            } else if (USER_ROLE.equals(role.getName())) {
                request.getSession().setAttribute(DRIVER, true);
            }
        }
    }
}
