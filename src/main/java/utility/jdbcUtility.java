package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcUtility {

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/database?useSSL=false";
    private static final String jdbcUserName = "root";
    private static final String jdbcPassword = "root";

    public static Connection connect() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL,
                    jdbcUserName, jdbcPassword);

        } catch (SQLException exception) {
            throw new SQLException(exception);
        }
        return connection;
    }
}