package fr.unice.iut.restaurant.exception;

/**
 * Classe d'exception
 * @author Ismael
 * @version 1.0
 */
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}