package modern.exceptions;

public class DateWrongException extends RuntimeException {
    private static final long serialVersionUID = 8559053230791117755L;

    public DateWrongException(String message) {
        super(message);
    }
}
