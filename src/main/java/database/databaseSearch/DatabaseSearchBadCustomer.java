package database.databaseSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSearchBadCustomer {
    private final Statement statement;

    public DatabaseSearchBadCustomer(Statement statement) {
        this.statement = statement;
    }

    public JSONArray find(int maxCustomer) throws SQLException {
        JSONArray jArrResult = new JSONArray();
        ResultSet resultSet = statement.executeQuery("select firstname, lastname, purchases_count from (\t\t\n" +
                "\tSELECT count(purchases.id) as purchases_count , customer_id from purchases \n" +
                "\t\tgroup by customer_id\n" +
                "\t\torder by purchases_count ASC\n" +
                "\t\tlimit " + maxCustomer +
                "\t) as result\n" +
                "\tJOIN customers ON customer_id = customers.id");
        while (resultSet.next()) {
            JSONObject jOLine = new JSONObject();
            jOLine.put(resultSet.getString("lastname"), resultSet.getString("firstname"));
            jArrResult.put(jOLine);
        }
        return jArrResult;
    }

}
