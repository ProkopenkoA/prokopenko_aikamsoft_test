package database.databaseStat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatIdCustomers {

    private final Statement statement;

    public StatIdCustomers(Statement statement) {
        this.statement = statement;
    }

    public List<Integer> getIdCustomersFromInterval(String startDate, String endDate) throws SQLException {
        List<Integer> customersId = new ArrayList<Integer>(100);
        ResultSet resultSet = statement.executeQuery("select customer_id from purchases \n" +
                "inner join (select working_day\n" +
                "    from (\n" +
                "        select working_day::timestamp from generate_series('" + startDate + "'::date, '" + endDate + "'::date, '1 day') working_day \n" +
                "\t\twhere extract(isodow from working_day::timestamp) < 6\n" +
                "    ) as working_days) as customers_id\n" +
                "\tON working_day = purchases.date\n" +
                "group by customer_id");
        while (resultSet.next()) {
            customersId.add(resultSet.getInt("customer_id"));
        }
        return customersId;
    }
}
