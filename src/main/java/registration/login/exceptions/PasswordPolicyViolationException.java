package registration.login.exceptions;

public class PasswordPolicyViolationException extends PasswordException {
    public PasswordPolicyViolationException(){
        super("Password does not meet the password ploicy");
    }

    public PasswordPolicyViolationException(String message){super(message);}

}
