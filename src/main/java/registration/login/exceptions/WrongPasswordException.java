package registration.login.exceptions;

public class WrongPasswordException extends PasswordException {
    public WrongPasswordException(){super("Incorrect password provided");}


    public WrongPasswordException(String message){super(message);}
}
