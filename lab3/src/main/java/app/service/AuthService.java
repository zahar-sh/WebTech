package app.service;

import app.dto.UserDto;
import app.enums.HttpStatus;
import app.enums.Role;
import app.exception.BaseRuntimeException;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AuthService {
    private final UserService service;

    public AuthService(UserService service) {
        this.service = Objects.requireNonNull(service);
    }

    public void checkAuth(UserDto dto) {
        if (dto == null || dto.getEndTime() == null) {
            throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, "");
        }
        service.getValidator().validate(dto);
        dto = service.findByEmail(dto.getEmail());
        if (dto == null || dto.getEndTime() == null ||
                System.currentTimeMillis() > dto.getEndTime()) {
            throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, "Time out");
        }
    }

    public void checkAdminAccess(UserDto user) {
        checkAuth(user);
        if (user.getRole() != Role.ADMIN) {
            throw new BaseRuntimeException(HttpStatus.FORBIDDEN, "Access");
        }
    }

    public UserDto register(UserDto dto) {
        service.getValidator().validate(dto);
        UserDto candidate = service.findByEmail(dto.getEmail());
        if (candidate != null) {
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "User exist");
        }
        String password = dto.getPassword();
        String hashedPassword = Crypt.hash(password);
        dto.setPassword(hashedPassword);
        setSessionDeadLine(dto);
        return service.save(dto);
    }

    public UserDto auth(UserDto dto) {
        service.getValidator().validate(dto);
        UserDto candidate = service.findByEmail(dto.getEmail());
        if (candidate == null) {
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        String password = dto.getPassword();
        String hashedPassword = candidate.getPassword();
        if (!Crypt.compare(password, hashedPassword)) {
            throw new BaseRuntimeException(HttpStatus.BAD_REQUEST, "Invalid password");
        }
        setSessionDeadLine(candidate);
        return service.save(candidate);
    }

    private void setSessionDeadLine(UserDto candidate) {
        long l = System.currentTimeMillis();
        candidate.setEndTime(l + TimeUnit.MINUTES.toMillis(5));
    }
}
