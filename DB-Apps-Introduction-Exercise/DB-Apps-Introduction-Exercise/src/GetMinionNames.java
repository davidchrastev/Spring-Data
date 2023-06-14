import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        int id = readInput();

        Connection connection = setUpConnection();

        PreparedStatement firstStatement = getFirstStatement(id, connection);

        PreparedStatement secondStatement = getSecondStatement(id, connection);

        ResultSet resultSet = firstStatement.executeQuery();

        printOutput(id, secondStatement, resultSet);
    }

    private static void printOutput(int id, PreparedStatement secondStatement, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String villainName = resultSet.getString(1);
            System.out.println("Villain: " + villainName);
            resultSet = secondStatement.executeQuery();
            int counter = 1;
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int age = resultSet.getInt(2);
                System.out.println(counter + ". " + name + " " + age);
                counter++;
            }
        } else {
            System.out.printf("No villain with ID %d exists in the database.\n", id);
        }
    }

    private static int readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter villain ID");
        return Integer.parseInt(scanner.nextLine());
    }

    private static PreparedStatement getSecondStatement(int id, Connection connection) throws SQLException {
        PreparedStatement secondStatement =
                connection.prepareStatement("SELECT\n" +
                        "    m.name as name,\n" +
                        "    m.age as age\n" +
                        "FROM villains v\n" +
                        "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                        "JOIN minions m on m.id = mv.minion_id\n" +
                        "WHERE mv.villain_id = ?");
        secondStatement.setInt(1, id);
        return secondStatement;
    }

    private static PreparedStatement getFirstStatement(int id, Connection connection) throws SQLException {
        PreparedStatement firstStatement =
                connection.prepareStatement("SELECT\n" +
                        "    name\n" +
                        "FROM villains\n" +
                        "WHERE id = ?");
        firstStatement.setInt(1, id);
        return firstStatement;
    }

    private static Connection setUpConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
    }
}
