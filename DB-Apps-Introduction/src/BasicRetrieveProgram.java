import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BasicRetrieveProgram {
    private static final String SELECT_USER_GAMES_COUNT_BY_USERNAME =
            "SELECT first_name, last_name, COUNT(ug.user_id) as count " +
            "FROM users u JOIN users_games ug on u.id = ug.user_id " +
            "WHERE user_name = ? " +
            "GROUP BY first_name, last_name";
    public static void main(String[] args) throws SQLException {
        start();
    }

    private static void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String user_name = scanner.nextLine();

        Connection connection = setUpConnection();

        PreparedStatement pr = getPreparedStatement(user_name, connection);

        ResultSet rs = pr.executeQuery();

        String response = getResponse(user_name, rs);

        System.out.println(response);
    }

    private static PreparedStatement getPreparedStatement(String user_name, Connection connection) throws SQLException {
        PreparedStatement pr = connection.prepareStatement(SELECT_USER_GAMES_COUNT_BY_USERNAME);

        pr.setString(1, user_name);
        return pr;
    }

    private static Connection setUpConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);
    }

    private static String getResponse(String user_name, ResultSet rs) throws SQLException {
        StringBuilder response = new StringBuilder();

        if (rs.next()) {
            int count = rs.getInt("count");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            response.append("User: ").append(user_name).append(System.lineSeparator());
            response.append(firstName).append(" ").append(lastName).append(" has played ").append(count).append(" games");
        } else {
            response.append("No such user exists");
        }

        return response.toString();
    }
}
