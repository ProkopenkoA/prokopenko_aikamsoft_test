package database.databaseStat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Pattern;

public class DatabaseStat {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseStat.class.getName());

    private final StatIdCustomers statIdCustomers;
    private final StatNamePersonById statNamePersonById;
    private final StatProductAndExpense statProductAndExpense;
    private final StatCountWorkingDay statCountWorkingDay;
    private final JSONObject jsonObject;

    private long totalExpenses = 0;

    public DatabaseStat(JSONObject jsonObject, Statement statement) {
        this.jsonObject = jsonObject;
        statIdCustomers = new StatIdCustomers(statement);
        statNamePersonById = new StatNamePersonById(statement);
        statProductAndExpense = new StatProductAndExpense(statement);
        statCountWorkingDay = new StatCountWorkingDay(statement);
    }

    public JSONObject createJSONObjectResult() {
        JSONObject jObjectResult = new JSONObject();
        try {
            String startDate = jsonObject.getString("startDate");
            String endDate = jsonObject.getString("endDate");

            String regexp = "[0-9]{4}-[0-1][0-2]-[0-3][0-9]";
            if (!Pattern.matches(regexp, startDate) || !Pattern.matches(regexp, endDate)) {
                throw new Exception("Неправильный формат даты");
            }

            List<Integer> customersId = statIdCustomers.getIdCustomersFromInterval(startDate, endDate);
            JSONArray jArrCustomers = createJArrCustomers(customersId, startDate, endDate);

            jObjectResult.put("type", "stat");
            jObjectResult.put("totalDays", statCountWorkingDay.find(startDate, endDate));
            jObjectResult.put("customers", jArrCustomers);
            jObjectResult.put("totalExpenses", totalExpenses);
            jObjectResult.put("avgExpenses", totalExpenses / customersId.size());

        } catch (JSONException e) {
            return createJObjError("Ненайден ключ - " + e.getMessage());
        } catch (SQLException e) {
            return createJObjError("Ошибка: в базе данных - " + e.getMessage());
        } catch (Exception e) {
            return createJObjError(e.getMessage());
        }
        return jObjectResult;
    }

    private JSONArray createJArrCustomers(List<Integer> customersId, String startDate, String endDate) throws SQLException {
        JSONArray jArrCustomers = new JSONArray();
        for (Integer customerId : customersId) {
            jArrCustomers.put(createJObjCustomer(customerId, startDate, endDate));
        }
        return jArrCustomers;
    }

    private JSONObject createJObjCustomer(int customer_id, String startDate, String endDate) throws SQLException {
        JSONObject jObjCustomer = new JSONObject();
        jObjCustomer.put("name", statNamePersonById.find(customer_id));
        jObjCustomer.put("purchases", statProductAndExpense.find(customer_id, startDate, endDate));
        int customerTotalExpenses = statProductAndExpense.getTotalExpensesAndToZero();
        jObjCustomer.put("totalExpenses", customerTotalExpenses);
        totalExpenses += customerTotalExpenses;
        return jObjCustomer;
    }

    private JSONObject createJObjError(String messege) {
        LOGGER.error("Error DatabaseStat: " + messege);
        JSONObject jObjError = new JSONObject();
        jObjError.put("type", "error");
        jObjError.put("message", messege);
        return jObjError;
    }
}
