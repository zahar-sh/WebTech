package app.service;


import app.dto.DocumentDto;
import app.model.Document;
import app.repository.DocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DocumentService {
    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public DocumentDto findById(Long id) {
        Document user = repository.findById(id);
        return DocumentDto.mapToDto(user);
    }

    public List<DocumentDto> findAll() {
        return repository.findAll()
                .stream()
                .map(DocumentDto::mapToDto)
                .collect(Collectors.toList());
    }

    public List<DocumentDto> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids)
                .stream()
                .map(DocumentDto::mapToDto)
                .collect(Collectors.toList());
    }

    public DocumentDto save(DocumentDto dto) {
        return DocumentDto.mapToDto(repository.save(DocumentDto.mapToEntity(dto)));
    }

    public List<DocumentDto> saveAll(Iterable<DocumentDto> dtos) {
        ArrayList<Document> users = new ArrayList<>();
        dtos.forEach(dto -> {
            users.add(DocumentDto.mapToEntity(dto));
        });
        return repository.saveAll(users)
                .stream()
                .map(DocumentDto::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAll(Iterable<DocumentDto> dtos) {
        ArrayList<Document> users = new ArrayList<>();
        dtos.forEach(dto -> users.add(DocumentDto.mapToEntity(dto)));
        repository.deleteAll(users);
    }
}
