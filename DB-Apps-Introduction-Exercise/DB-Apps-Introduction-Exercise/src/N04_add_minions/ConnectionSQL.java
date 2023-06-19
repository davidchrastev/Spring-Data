package N04_add_minions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSQL {
    public static Connection setUpConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
    }
}
