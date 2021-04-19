package registration.login.exceptions;


    public class ApiException extends RuntimeException {

        public ApiException() {
            super("Failed to perform the requested action");
        }

        public ApiException(Throwable cause) {
            super("Failed to perform the requested action", cause);
        }

        public ApiException(String message) {
            super(message);
        }

        public ApiException(String message, Throwable cause) {
            super(message, cause);
        }
    }


