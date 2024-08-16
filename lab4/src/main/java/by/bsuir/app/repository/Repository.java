package by.bsuir.app.repository;

import by.bsuir.app.exception.RepositoryException;
import by.bsuir.app.specification.Specification;

import java.util.List;
import java.util.Optional;


public interface Repository<T> {


    Optional<T> query(Specification specification) throws RepositoryException;


    List<T> queryAll(Specification specification) throws RepositoryException;


    void save(T item) throws RepositoryException;

    String getTableName();
}
