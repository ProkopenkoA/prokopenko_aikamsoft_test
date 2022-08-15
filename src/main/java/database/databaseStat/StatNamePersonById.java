package database.databaseStat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatNamePersonById {
    private final Statement statement;

    public StatNamePersonById(Statement statement) {
        this.statement = statement;
    }

    public String find(int customers_id) throws SQLException {
        String name = null;
        ResultSet resultSet = statement.executeQuery("SELECT firstname, lastname FROM customers\n" +
                "WHERE id = " + customers_id);
        while (resultSet.next()) {
            name = resultSet.getString("lastname") + " " + resultSet.getString("firstname");
        }
        return name;
    }
}
