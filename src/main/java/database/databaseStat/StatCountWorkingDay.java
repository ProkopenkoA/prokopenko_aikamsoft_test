package database.databaseStat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatCountWorkingDay {
    private final Statement statement;

    public StatCountWorkingDay(Statement statement) {
        this.statement = statement;
    }

    public int find(String startDate, String endDate) throws SQLException {
        int countDays = 0;
        ResultSet resultSet = statement.executeQuery("select count(working_day) as count_days \n" +
                "    from (\n" +
                "        select working_day::timestamp from generate_series('" + startDate + "'::date, '" + endDate + "'::date, '1 day') working_day \n" +
                "\t\twhere extract(isodow from working_day::timestamp) < 6\n" +
                "    ) as working_days");
        while (resultSet.next()) {
            countDays = resultSet.getInt("count_days");
        }
        return countDays;
    }
}
