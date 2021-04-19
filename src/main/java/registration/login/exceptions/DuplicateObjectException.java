package registration.login.exceptions;

public class DuplicateObjectException extends ApiException {

    public DuplicateObjectException(){super("The target object already exists");}

    public DuplicateObjectException(String message){super(message);}
}