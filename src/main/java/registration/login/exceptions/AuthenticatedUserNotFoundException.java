package registration.login.exceptions;


/**
 * A {@link RuntimeException} thrown when user resources are not found.
 */
public class AuthenticatedUserNotFoundException extends ApiException {

    public AuthenticatedUserNotFoundException() {
        super("No Authenticated user was found");
    }
}
