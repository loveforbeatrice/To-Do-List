package Business;

public class NoSuchJobException extends Exception {
    public NoSuchJobException() {
        super("Chosen ID doesn't match with any job");
    }
    public NoSuchJobException(String message) {
        super(message);
    }

    public NoSuchJobException(Throwable cause) {
        super(cause);
    }

    public NoSuchJobException(String message, Throwable cause) {
        super(message, cause);
    }
}
