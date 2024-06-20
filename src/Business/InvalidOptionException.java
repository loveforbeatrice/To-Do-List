package Business;

public class InvalidOptionException extends Exception {

    public InvalidOptionException() {
        super("Invalid option! Please enter a valid option ");
    }

    public InvalidOptionException(String message) {
        super(message);
    }

    public InvalidOptionException(Throwable cause) {
        super(cause);
    }

    public InvalidOptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
