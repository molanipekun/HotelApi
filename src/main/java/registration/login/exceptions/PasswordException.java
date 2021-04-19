package registration.login.exceptions;

public class PasswordException extends ApiException {
    public PasswordException(){super("Failed to process password");}

    public PasswordException(Throwable cause){super("Failed to process password",cause);}


    public PasswordException(String message){super(message);}

    public PasswordException(String message, Throwable cause){super(message,cause);}
}
