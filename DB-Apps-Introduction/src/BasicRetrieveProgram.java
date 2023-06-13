import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BasicRetrieveProgram {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        String user = scanner.nextLine();

        PreparedStatement pr = connection.prepareStatement("SELECT first_name, last_name, COUNT(ug.user_id) as count FROM users u JOIN users_games ug on u.id = ug.user_id WHERE user_name = ? GROUP BY first_name, last_name");
        pr.setString(1, user);

        ResultSet rs = pr.executeQuery();

        if (rs.next()) {
            int count = rs.getInt("count");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            System.out.println("User: " + user);
            System.out.println(firstName + " " + lastName + " has played " + count + " games");
        } else {
            System.out.println("No such user exists");
        }


    }
}
