package app.service;

import app.dto.UserDto;
import app.model.User;
import app.repository.UserRepository;
import app.validator.UserDtoValidator;
import app.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {
    private final UserDtoValidator validator = new UserDtoValidator();
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public UserDto findById(Integer id) {
        User user = repository.findById(id);
        return UserDto.mapToDto(user);
    }

    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto findByEmail(String email) {
        return UserDto.mapToDto(repository.findByEmail(email));
    }


    public List<UserDto> findAllById(Iterable<Integer> ids) {
        return repository.findAllById(ids)
                .stream()
                .map(UserDto::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto save(UserDto dto) {
        validator.validate(dto);
        return UserDto.mapToDto(repository.save(UserDto.mapToEntity(dto)));
    }

    public List<UserDto> saveAll(Iterable<UserDto> dtos) {
        ArrayList<User> users = new ArrayList<>();
        dtos.forEach(dto -> {
            validator.validate(dto);
            users.add(UserDto.mapToEntity(dto));
        });
        return repository.saveAll(users)
                .stream()
                .map(UserDto::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAll(Iterable<UserDto> dtos) {
        ArrayList<User> users = new ArrayList<>();
        dtos.forEach(dto -> {
            validator.validate(dto);
            users.add(UserDto.mapToEntity(dto));
        });
        repository.deleteAll(users);
    }

    public Validator<UserDto> getValidator() {
        return validator;
    }
}
