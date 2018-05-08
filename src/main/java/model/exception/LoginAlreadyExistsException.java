package model.exception;

public class LoginAlreadyExistsException extends Exception {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
