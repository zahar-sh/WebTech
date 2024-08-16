package app.repository.impl;

import app.repository.Repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ListRepository<T, ID> implements Repository<T, ID> {
    protected final List<T> data = new ArrayList<>();

    @Override
    public T findById(ID id) {
        return id == null ? null :
                data.stream()
                        .filter(t -> id.equals(getId(t)))
                        .findAny()
                        .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        List<T> result = new ArrayList<>();
        ids.forEach(id -> result.add(findById(id)));
        return result;
    }

    @Override
    public T save(T t) {
        if (t == null)
            return null;
        ID id = getId(t);
        if (id != null) {
            ListIterator<T> iterator = data.listIterator();
            while (iterator.hasNext()) {
                if (id.equals(getId(iterator.next()))) {
                    iterator.set(t);
                    return t;
                }
            }
        }
        T element = newElementFrom(t);
        data.add(element);
        return element;
    }

    @Override
    public List<T> saveAll(Iterable<T> ts) {
        if (ts == null)
            return Collections.emptyList();
        List<T> result = new ArrayList<>();
        ts.forEach(t -> result.add(save(t)));
        return result;
    }

    @Override
    public void delete(T t) {
        if (t == null) return;
        deleteById(getId(t));
    }


    @Override
    public void deleteById(ID id) {
        if (id == null) return;
        data.removeIf(t1 -> id.equals(getId(t1)));
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public void deleteAll(Iterable<T> ts) {
        if (ts == null) return;
        ts.forEach(this::delete);
    }

    @Override
    public void deleteAllById(Iterable<ID> ids) {
        if (ids == null) return;
        ids.forEach(this::deleteById);
    }

    protected abstract ID getId(T t);

    protected abstract T newElementFrom(T t);
}
