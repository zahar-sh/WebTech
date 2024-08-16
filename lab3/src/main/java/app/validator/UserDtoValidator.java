package app.validator;

import app.dto.UserDto;
import app.exception.ValidatorException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserDtoValidator implements Validator<UserDto> {
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    private static final ValidatorException[] EXCEPTIONS = {};

    @Override
    public ValidatorException[] validate0(UserDto userDto) {
        ArrayList<ValidatorException> exceptionsBuilder = new ArrayList<>();

        if (!(userDto.getEmail() == null || emailPattern.matcher(userDto.getEmail()).matches())) {
            exceptionsBuilder.add(new ValidatorException("Email", userDto.getEmail()));
        }

        return exceptionsBuilder.toArray(EXCEPTIONS);
    }
}
