package database.databaseSearch;

import dto.SearchOperationKeys;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumMap;
import java.util.Iterator;

public class DatabaseSearch {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSearch.class.getName());

    private final DatabaseSearchByPerson databaseSearchByPerson;
    private final DatabaseSearchByProductName databaseSearchByProductName;
    private final DatabaseSearchByCost databaseSearchByCost;
    private final DatabaseSearchBadCustomer databaseSearchBadCustomer;

    private final JSONObject jsonObject;

    public DatabaseSearch(JSONObject jsonObject, Statement statement) {
        this.jsonObject = jsonObject;
        databaseSearchByPerson = new DatabaseSearchByPerson(statement);
        databaseSearchByProductName = new DatabaseSearchByProductName(statement);
        databaseSearchByCost = new DatabaseSearchByCost(statement);
        databaseSearchBadCustomer = new DatabaseSearchBadCustomer(statement);
    }

    public JSONObject createJsonObject() {
        JSONObject resultJson = new JSONObject();
        try {
            JSONArray jArrInput = (JSONArray) jsonObject.get("criterias");

            JSONArray jArrResult = new JSONArray();
            JSONObject jCriteria;

            for (int index = 0; index < jArrInput.length(); index++) {
                jCriteria = (JSONObject) jArrInput.get(index);
                jArrResult.put(createJObjResult(jCriteria));
            }

            resultJson.put("type", "search");
            resultJson.put("results", jArrResult);
        } catch (JSONException e) {
            return createJObjError("Ненайден ключ - " + e.getMessage());
        } catch (SQLException e) {
            return createJObjError("Ошибка: в базе данных - " + e.getMessage());
        } catch (Exception e) {
            return createJObjError(e.getMessage());
        }
        return resultJson;
    }

    private JSONObject createJObjResult(JSONObject jObjCriteria) throws SQLException {
        JSONObject jObjResult = new JSONObject();

        Iterator<String> keysItr = jObjCriteria.keys();
        EnumMap<SearchOperationKeys, Object> keysMap = new EnumMap<>(SearchOperationKeys.class);
        String key;
        while (keysItr.hasNext()) {
            key = keysItr.next();
            for (SearchOperationKeys sOpKey : SearchOperationKeys.values()) {
                if (sOpKey.getKey().equals(key)) {
                    keysMap.put(sOpKey, jObjCriteria.get(key));
                    break;
                }
            }
        }

        jObjResult.put("criteria", jObjCriteria);
        if (keysMap.get(SearchOperationKeys.LAST_NAME) != null) {
            if (jObjCriteria.length() != 1) {
                return createJObjWrongCriteria(jObjCriteria);
            }
            String lastName = (String) keysMap.get(SearchOperationKeys.LAST_NAME);
            jObjResult.put("results", databaseSearchByPerson.find(lastName));
        }

        if (keysMap.get(SearchOperationKeys.PRODUCT_NAME) != null && keysMap.get(SearchOperationKeys.MIN_TIMES) != null) {
            if (jObjCriteria.length() != 2) {
                return createJObjWrongCriteria(jObjCriteria);
            }
            String productName = (String) keysMap.get(SearchOperationKeys.PRODUCT_NAME);
            int minTimes = (int) keysMap.get(SearchOperationKeys.MIN_TIMES);
            jObjResult.put("results", databaseSearchByProductName.find(productName, minTimes));
        }

        if (keysMap.get(SearchOperationKeys.MIN_EXPENSES) != null && keysMap.get(SearchOperationKeys.MAX_EXPENSES) != null) {
            if (jObjCriteria.length() != 2) {
                return createJObjWrongCriteria(jObjCriteria);
            }
            int minExpenses = (int) keysMap.get(SearchOperationKeys.MIN_EXPENSES);
            int maxExpenses = (int) keysMap.get(SearchOperationKeys.MAX_EXPENSES);
            jObjResult.put("results", databaseSearchByCost.find(minExpenses, maxExpenses));
        }

        if (keysMap.get(SearchOperationKeys.BAD_CUSTOMERS) != null) {
            if (jObjCriteria.length() != 1) {
                return createJObjWrongCriteria(jObjCriteria);
            }
            int badCustomer = (int) keysMap.get(SearchOperationKeys.BAD_CUSTOMERS);
            jObjResult.put("results", databaseSearchBadCustomer.find(badCustomer));
        }
        if (jObjResult.has("results")) {
            return jObjResult;
        }
        return jObjResult.put("results", "Критерий не найден");
    }

    private JSONObject createJObjError(String messege) {
        LOGGER.error("Error DatabaseSearch: " + messege);
        JSONObject jObjError = new JSONObject();
        jObjError.put("type", "error");
        jObjError.put("message", messege);
        return jObjError;
    }

    private JSONObject createJObjWrongCriteria(JSONObject criteria) {
        JSONObject jObjWrong = new JSONObject();
        jObjWrong.put("criteria", criteria);
        jObjWrong.put("results", "Ошибка: не правильный критерий");
        return jObjWrong;
    }
}





