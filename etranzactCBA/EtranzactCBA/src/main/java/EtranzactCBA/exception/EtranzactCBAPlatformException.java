package EtranzactCBA.exception;

public class EtranzactCBAPlatformException extends RuntimeException {

    public EtranzactCBAPlatformException(String message) {
        super(message);
    }

    public EtranzactCBAPlatformException(String message, Throwable cause) {
        super(message, cause);
    }
}