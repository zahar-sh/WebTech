package app.repository;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T, ID> {
    T findById(ID id);

    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);

    T save(T t);
    List<T> saveAll(Iterable<T> ts);

    void delete(T t);
    void deleteById(ID t);
    void deleteAll();
    void deleteAll(Iterable<T> ts);
    void deleteAllById(Iterable<ID> ids);
}