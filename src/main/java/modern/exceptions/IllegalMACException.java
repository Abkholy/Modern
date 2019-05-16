package modern.exceptions;

public class IllegalMACException extends RuntimeException {
    private static final long serialVersionUID = -1646939176462497394L;

    public IllegalMACException(String message) {
        super(message);
    }
}
