package database;

import database.databaseSearch.DatabaseSearch;
import database.databaseStat.DatabaseStat;
import dto.OperationType;
import org.json.JSONObject;

import java.sql.SQLException;

public class DatabaseOperation {
    JSONObject jObjResult;

    public DatabaseOperation() {
    }

    public void perform(OperationType operationType, JSONObject jsonObject) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        if (operationType == OperationType.SEARCH) {
            DatabaseSearch databaseSearch = new DatabaseSearch(jsonObject, databaseConnection.getStatement());
            jObjResult = databaseSearch.createJsonObject();
        }
        if (operationType == OperationType.STAT) {
            DatabaseStat databaseStat = new DatabaseStat(jsonObject, databaseConnection.getStatement());
            jObjResult = databaseStat.createJSONObjectResult();
        }
        databaseConnection.closeStatement();
    }

    public JSONObject getJObjResult() {
        return jObjResult;
    }
}
