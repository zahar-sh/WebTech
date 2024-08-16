package app.client.utils;

import app.client.exception.KeyNotFoundException;
import app.client.exception.ValueNotFoundException;

import java.util.function.Function;
import java.util.stream.IntStream;

public class ArgsParser {
    public static String getValue(String[] args, int from, int to, String key)
            throws KeyNotFoundException, ValueNotFoundException {
        int keyPos = IntStream.range(from, to)
                .filter(value -> key.equals(args[value]))
                .findAny()
                .orElse(-1);
        if (keyPos < 0)
            throw new KeyNotFoundException(String.format("key '%s' not found", key));
        int valuePos = keyPos + 1;
        if (valuePos > to) {
            throw new ValueNotFoundException(String.format("value not found after key '%s'", key));
        }
        return args[valuePos];
    }

    public static <T> T getValue(String[] args, int from, int to, String key,
                                 boolean required, Function<String, T> mapper) {
        try {
            String value = getValue(args, from, to, key);
            return mapper.apply(value);
        } catch (KeyNotFoundException e) {
            if (required)
                throw e;
            return null;
        }
    }

    public static <T extends Number> T getNumber(String[] args, int off, int len, String key,
                                                 boolean required, Function<String, T> mapper) {
        return getValue(args, off, len, key, required, s -> {
            try {
                return mapper.apply(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("'%s' is not a number", s));
            }
        });
    }
}
