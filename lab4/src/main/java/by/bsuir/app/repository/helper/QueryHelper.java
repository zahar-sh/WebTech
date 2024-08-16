package by.bsuir.app.repository.helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryHelper {

    public static String makeInsertQuery(Map<String, Object> fields, String table) {
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        fields.forEach((column, value) -> {
            if (value != null) {
                columns.append(" `").append(column).append("`,");
                values.append(" ?,");
            }
        });

        values.deleteCharAt(values.lastIndexOf(","));
        columns.deleteCharAt(columns.lastIndexOf(","));
        values.append(")");
        columns.append(")");

        return String.format("INSERT INTO %s%s VALUES%s", table, columns, values);
    }

    public static String makeUpdateQuery(Map<String, Object> fields, String table) {
        StringBuilder query = new StringBuilder();
        fields.forEach((column, value) -> {
            if (value != null) {
                if (column.equals("id")) {
                    query.deleteCharAt(query.lastIndexOf(","));
                    query.append(" WHERE `id` =?");
                } else {
                    query.append(" `").append(column).append("`=? ,");
                }
            }
        });
        return String.format("UPDATE %s SET %s", table, query);
    }

    public static void prepare(PreparedStatement preparedStatement, Map<String, Object> fields) throws SQLException {
        int i = 1;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                preparedStatement.setString(i++, String.valueOf(value));
            }
        }
    }

    public static void prepare(PreparedStatement preparedStatement, List<Object> params) throws SQLException {
        int length = params.size();
        for (int i = 0; i < length; i++) {
            Object param = params.get(i);
            if (param instanceof Integer) {
                preparedStatement.setInt(i + 1, (Integer) param);
            } else {
                preparedStatement.setString(i + 1, String.valueOf(param));
            }
        }
    }
}
