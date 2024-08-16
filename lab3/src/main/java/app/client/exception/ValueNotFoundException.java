package app.client.exception;

public class ValueNotFoundException extends IllegalArgumentException {
    public ValueNotFoundException(String s) {
        super(s);
    }
}
