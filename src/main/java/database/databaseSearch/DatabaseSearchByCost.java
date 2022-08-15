package database.databaseSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSearchByCost {
    private final Statement statement;

    public DatabaseSearchByCost(Statement statement) {
        this.statement = statement;
    }

    public JSONArray find(long minCost, long maxCost) throws SQLException {
        JSONArray jArrResult = new JSONArray();
        ResultSet resultSet = statement.executeQuery("select firstname, lastname from (\t\n" +
                "\tSELECT SUM(price) as customer_cost , customer_id from products \n" +
                "\t\t\tJOIN purchases ON product_id = products.id\n" +
                "\t\t\tgroup by customer_id \n" +
                "\t) as result\n" +
                "\tJOIN customers ON customer_id = customers.id\n" +
                "\twhere customer_cost > " + minCost + " AND customer_cost < " + maxCost);
        while (resultSet.next()) {
            JSONObject jOLine = new JSONObject();
            jOLine.put(resultSet.getString("lastname"), resultSet.getString("firstname"));
            jArrResult.put(jOLine);
        }
        return jArrResult;
    }
}
