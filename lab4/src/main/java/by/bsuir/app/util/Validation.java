package by.bsuir.app.util;

import java.util.Map;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern ID_PATTERN = Pattern.compile("^([1-9]{1}[0-9]{0,10})$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
    private static final Pattern PRICE_PATTERN = Pattern.compile("^([1-9]{1}[0-9]{0,8})$");
    private static final Pattern ROOM_NUMBER_PATTERN = Pattern.compile("^([0-9]{3})$");

    private String invalidData;

    private Pattern definePattern(String type) {
        switch (type) {
            case "username":
                return USERNAME_PATTERN;
            case "refill":
                return PRICE_PATTERN;
            case "roomNumber":
                return ROOM_NUMBER_PATTERN;
            case "roomId":
                return ID_PATTERN;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public boolean isValid(Map<String, String> inputData) {
        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String value = entry.getValue();
            if (value == null) return false;
            String key = entry.getKey();
            Pattern pattern = definePattern(key);
            if (!pattern.matcher(value).matches()) {
                invalidData = key;
                return false;
            }
        }
        return true;
    }

    public String getInvalidData() {
        return invalidData;
    }
}
