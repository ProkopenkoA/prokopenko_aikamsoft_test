package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class.getName());
    private static String DataBase_URL;
    private static String USER;
    private static String PASS;

    static {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(ClassLoader.getSystemResource("config.properties").getPath())) {
            properties.load(fis);
            DataBase_URL = properties.getProperty("DataBase_URL");
            USER = properties.getProperty("USER");
            PASS = properties.getProperty("PASS");
        } catch (Exception e) {
            LOGGER.error("Ошибка при чтение properties файла - ", e);
        }
    }

    private Statement statement;

    public DatabaseConnection() {
    }

    public void connect() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(DataBase_URL, USER, PASS);
        statement = connection.createStatement();
    }

    public void closeStatement() {
        try {
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Sql close error: ", e);
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
