package fr.unice.iut.authentication;

public class UnauthorizedException extends AuthorizationCheckException {

    public UnauthorizedException(String s) {
        super(s);
    }
}
