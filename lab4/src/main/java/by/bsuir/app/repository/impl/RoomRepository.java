package by.bsuir.app.repository.impl;

import by.bsuir.app.builder.RoomBuilder;
import by.bsuir.app.entity.Room;
import by.bsuir.app.exception.RepositoryException;
import by.bsuir.app.repository.AbstractRepository;
import by.bsuir.app.specification.Specification;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomRepository extends AbstractRepository<Room, Integer> {
    private final RoomBuilder builder = new RoomBuilder();

    public RoomRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Room item) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("id", item.getId());
        values.put("room_number", item.getRoomNumber());
        values.put("occupied", item.getOccupied());

        return values;
    }

    @Override
    public String getTableName() {
        return " `room` ";
    }

    @Override
    public Optional<Room> query(Specification specification) throws RepositoryException {
        String query = "SELECT * FROM `room` " + specification.toSql();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Room> queryAll(Specification specification) throws RepositoryException {
        String query = "SELECT * FROM `room` " + specification.toSql();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
