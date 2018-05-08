package model.service;

import model.entity.User;
import model.exception.IncorrectDataForUserException;
import model.exception.LoginAlreadyExistsException;
import model.exception.LoginNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User findByEmail(String email) throws LoginNotFoundException;

    User register(User user) throws LoginAlreadyExistsException, IncorrectDataForUserException;

    User getCurrentUser(HttpServletRequest request);
}
