package controller.command;

import view.ExceptionMessage;
import view.Messages;
import view.constant.general.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocale(request);
        return request.getParameter(Parameters.PAGE);
    }

    private void setLocale(HttpServletRequest request) {
        String lang = request.getParameter(Parameters.LANGUAGE);
        if (lang.equals(Parameters.EN)) {
            request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.EN);
            Messages.setLocale(Messages.ENGLISH);
            ExceptionMessage.setLocale(ExceptionMessage.ENGLISH);
        } else {
            request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.UA);
            Messages.setLocale(Messages.UKRAINIAN);
            ExceptionMessage.setLocale(ExceptionMessage.UKRAINIAN);
        }
    }
}