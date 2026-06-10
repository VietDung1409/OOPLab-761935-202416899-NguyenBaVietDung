package hust.soict.hedspi.aims.exception;

public class CartFullException extends IllegalStateException {
    public CartFullException(String message) {
        super(message);
    }
}
