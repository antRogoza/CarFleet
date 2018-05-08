package controller.command;

import model.entity.User;
import model.exception.IncorrectDataForUserException;
import model.exception.LoginAlreadyExistsException;
import model.service.UserService;
import view.ExceptionMessage;
import view.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static view.constant.general.Parameters.*;
public class RegistrationCommand implements Command {
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            userService.register(
                    new User.UserBuilder()
                            .setEmail(email)
                            .setPassword(password)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .buildUser()
            );
        } catch (LoginAlreadyExistsException | IncorrectDataForUserException e) {
            request.getSession().setAttribute(EXCEPTION, ExceptionMessage.getMessage(e.getMessage()));
            return Pages.REGISTRATION;
        }
        return Pages.LOGIN;
    }
}
