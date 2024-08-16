package app.client.exception;

public class KeyNotFoundException extends IllegalArgumentException {
    public KeyNotFoundException(String s) {
        super(s);
    }
}
