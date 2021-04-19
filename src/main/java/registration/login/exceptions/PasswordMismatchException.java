package registration.login.exceptions;

public class PasswordMismatchException extends PasswordException {

    public PasswordMismatchException(){super("Passwords do not match");}


    public PasswordMismatchException(String message){super(message);}

}
