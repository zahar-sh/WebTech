package app.controller;

import app.dto.UserDto;
import app.service.AuthService;
import app.service.UserService;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final UserService service;
    private final AuthService authService;

    public UserController(UserService service, AuthService authService) {
        this.service = Objects.requireNonNull(service);
        this.authService = Objects.requireNonNull(authService);
    }

    public UserDto findById(UserDto user, Integer id) {
        authService.checkAuth(user);
        return service.findById(id);
    }

    public List<UserDto> findAll(UserDto user) {
        authService.checkAuth(user);
        return service.findAll();
    }

    public UserDto findByEmail(UserDto user, String email) {
        authService.checkAuth(user);
        return service.findByEmail(email);
    }

    public List<UserDto> findAllById(UserDto user, Iterable<Integer> ids) {
        authService.checkAuth(user);
        return service.findAllById(ids);
    }

    public UserDto save(UserDto user, UserDto dto) {
        authService.checkAdminAccess(user);
        return service.save(dto);
    }

    public List<UserDto> saveAll(UserDto user, Iterable<UserDto> dtos) {
        authService.checkAdminAccess(user);
        return service.saveAll(dtos);
    }

    public void deleteById(UserDto user, Integer id) {
        authService.checkAdminAccess(user);
        service.deleteById(id);
    }

    public void deleteAll(UserDto user) {
        authService.checkAdminAccess(user);
        service.deleteAll();
    }

    public void deleteAll(UserDto user, Iterable<UserDto> dtos) {
        authService.checkAdminAccess(user);
        service.deleteAll(dtos);
    }

    public UserDto register(UserDto user) {
        return authService.register(user);
    }

    public UserDto auth(UserDto user) {
        return authService.auth(user);
    }
}
