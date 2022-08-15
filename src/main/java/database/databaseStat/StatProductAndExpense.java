package database.databaseStat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatProductAndExpense {
    private final Statement statement;
    private int totalExpenses = 0;

    public StatProductAndExpense(Statement statement) {
        this.statement = statement;
    }

    public JSONArray find(int customerId, String startDate, String endDate) throws SQLException {
        JSONArray jArrPurchases = new JSONArray();
        ResultSet resultSet = statement.executeQuery("select name, (price * count_purchases) as total_price from products \n" +
                "inner join(\n" +
                "\tselect product_id, count(product_id) as count_purchases from purchases \n" +
                "\tinner join (select working_day\n" +
                "\t\tfrom (\n" +
                "\t\t\tselect working_day::timestamp from generate_series('" + startDate + "'::date, '" + endDate + "'::date, '1 day') working_day \n" +
                "\t\t\twhere extract(isodow from working_day::timestamp) < 6\n" +
                "\t\t)as working_days) as customers_id\n" +
                "\t\tON working_day = purchases.date\n" +
                "\twhere customer_id = " + customerId +
                "\tgroup by product_id\n" +
                "\t)as product_expenses\n" +
                "\ton product_id = products.id");
        while (resultSet.next()) {
            JSONObject jObj = new JSONObject();
            jObj.put("name", resultSet.getString("name"));
            jObj.put("expenses", resultSet.getInt("total_price"));
            totalExpenses = totalExpenses + resultSet.getInt("total_price");
            jArrPurchases.put(jObj);
        }
        return jArrPurchases;
    }

    public int getTotalExpensesAndToZero() {
        int result = totalExpenses;
        totalExpenses = 0;
        return result;
    }
}
