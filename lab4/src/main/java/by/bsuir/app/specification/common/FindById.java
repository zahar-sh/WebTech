package by.bsuir.app.specification.common;

import by.bsuir.app.specification.Specification;

import java.util.Collections;
import java.util.List;

public class FindById implements Specification {

    private final Integer id;

    public FindById(Integer id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return "where id = ?";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}
