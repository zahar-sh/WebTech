package by.bsuir.app.repository;

import by.bsuir.app.builder.Builder;
import by.bsuir.app.entity.Entity;
import by.bsuir.app.exception.RepositoryException;
import by.bsuir.app.repository.helper.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity<ID>, ID> implements Repository<T> {

    private final Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    public List<T> executeQuery(String sql, Builder<T> builder, List<Object> params) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            QueryHelper.prepare(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return objects;
    }

    public Optional<T> executeQueryForSingleResult(String query, Builder<T> builder, List<Object> params) throws RepositoryException {
        List<T> items = executeQuery(query, builder, params);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    public void save(T item) throws RepositoryException {
        try {
            String sql;
            if (item.getId() != null) {
                sql = QueryHelper.makeUpdateQuery(getFields(item), getTableName());
            } else {
                sql = QueryHelper.makeInsertQuery(getFields(item), getTableName());
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            QueryHelper.prepare(preparedStatement, getFields(item));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }


    protected abstract Map<String, Object> getFields(T item);
}
