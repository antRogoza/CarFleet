package controller.command;

import view.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        return Pages.REGISTRATION;
    }
}