package framework.spawn.exceptions;

public class UnrecoverableException extends RuntimeException {
    public UnrecoverableException(Throwable cause) {
        super(cause);
    }

    public UnrecoverableException(String message, Throwable cause) {
        super(message, cause);
    }
}