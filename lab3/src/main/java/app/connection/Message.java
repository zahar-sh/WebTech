package app.connection;

import app.enums.MessageType;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
    private final MessageType type;
    private final Object[] args;

    public Message(MessageType type, Object... args) {
        this.type = Objects.requireNonNull(type);
        this.args = args;
    }

    public MessageType getType() {
        return type;
    }

    public Object[] getArgs() {
        return args;
    }
}
