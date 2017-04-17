package fr.unice.iut.restaurant.exception;

/**
 * Created by Utilisateur on 17/04/2017.
 */
public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String message){
        super(message);
    }
}
