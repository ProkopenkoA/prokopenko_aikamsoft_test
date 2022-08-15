package database.databaseSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSearchByPerson {
    private final Statement statement;

    public DatabaseSearchByPerson(Statement statement) {
        this.statement = statement;
    }

    public JSONArray find(String lastName) throws SQLException {
        JSONArray jArrResult = new JSONArray();
        ResultSet resultSet = statement.executeQuery("SELECT firstname FROM customers " +
                "WHERE lastname = '" + lastName + "'");
        while (resultSet.next()) {
            JSONObject jOLine = new JSONObject();
            jOLine.put(lastName, resultSet.getString("firstname"));
            jArrResult.put(jOLine);
        }
        return jArrResult;
    }
}
