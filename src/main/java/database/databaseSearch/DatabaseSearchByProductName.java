package database.databaseSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSearchByProductName {
    private final Statement statement;

    public DatabaseSearchByProductName(Statement statement) {
        this.statement = statement;
    }

    public JSONArray find(String productName, int minTimes) throws SQLException {
        JSONArray jArrResult = new JSONArray();
        ResultSet resultSet = statement.executeQuery("select firstname, lastname from (\n" +
                "\t\tSELECT count(purchases.id) as purchases_count , customer_id from purchases \n" +
                "\t\tJOIN products ON product_id = products.id\n" +
                "\t\twhere name = '" + productName + "'\n" +
                "\t\tgroup by customer_id \n" +
                "\t\t\t) AS result\n" +
                "\tJOIN customers ON customer_id = customers.id\n" +
                "\twhere purchases_count > " + minTimes);
        while (resultSet.next()) {
            JSONObject jOLine = new JSONObject();
            jOLine.put(resultSet.getString("lastname"), resultSet.getString("firstname"));
            jArrResult.put(jOLine);
        }
        return jArrResult;
    }
}