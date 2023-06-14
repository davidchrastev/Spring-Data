import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = setUpConnection();

        PreparedStatement statement = getPreparedStatement(connection);

        printResult(statement);
    }

    private static Connection setUpConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
    }

    private static PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT" +
                "    CONCAT_WS(' ', v.name, COUNT(mv.minion_id)) AS Output\n" +
                "FROM villains v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING COUNT(mv.minion_id) > 15\n" +
                "ORDER BY COUNT(mv.minion_id) DESC");
    }

    private static void printResult(PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            System.out.println(result.getString("Output"));
        }
    }
}
