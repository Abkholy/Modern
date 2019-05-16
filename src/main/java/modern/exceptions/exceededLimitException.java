package modern.exceptions;

public class exceededLimitException extends RuntimeException {
    private static final long serialVersionUID = -6871486103882914689L;

    public exceededLimitException(String message) {
        super(message);
    }
}
