package app.controller;

import app.dto.DocumentDto;
import app.dto.UserDto;
import app.service.AuthService;
import app.service.DocumentService;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public class DocumentController {
    private final DocumentService service;
    private final AuthService authService;

    public DocumentController(DocumentService service, AuthService authService) {
        this.service = Objects.requireNonNull(service);
        this.authService = Objects.requireNonNull(authService);
    }

    public DocumentDto findById(UserDto user, Long id) {
        authService.checkAuth(user);
        return service.findById(id);
    }

    public List<DocumentDto> findAll(UserDto user) {
        authService.checkAuth(user);
        return service.findAll();
    }

    public List<DocumentDto> findAllById(UserDto user, Iterable<Long> ids) {
        authService.checkAuth(user);
        return service.findAllById(ids);
    }

    public DocumentDto save(UserDto user, DocumentDto dto) {
        authService.checkAdminAccess(user);
        return service.save(dto);
    }

    public List<DocumentDto> saveAll(UserDto user, Iterable<DocumentDto> dtos) {
        authService.checkAdminAccess(user);
        return service.saveAll(dtos);
    }

    public void deleteById(UserDto user, Long id) {
        authService.checkAdminAccess(user);
        service.deleteById(id);
    }

    public void deleteAll(UserDto user) {
        authService.checkAdminAccess(user);
        service.deleteAll();
    }

    public void deleteAll(UserDto user, Iterable<DocumentDto> dtos) {
        authService.checkAdminAccess(user);
        service.deleteAll(dtos);
    }
}

