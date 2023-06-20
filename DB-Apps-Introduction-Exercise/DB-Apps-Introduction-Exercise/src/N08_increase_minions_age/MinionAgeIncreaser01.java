package N08_increase_minions_age;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MinionAgeIncreaser01 {

    public static final String PRINT_MINIONS_PATTERN = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.setUpConnection();
        List<Integer> minionIds = readMinionIds();

        updateMinionAgeAndNameToLowerCase(connection, minionIds);

        printAllMinions();
    }

    private static void printAllMinions() {
        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement preparedStatement =
                    connection
                            .prepareStatement(MinionAgeIncreaserStatements.SELECT_NAME_AND_AGE_FROM_MINIONS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.printf(PRINT_MINIONS_PATTERN, name, age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateMinionAgeAndNameToLowerCase(Connection connection, List<Integer> minionIds) throws SQLException {
        for (Integer minionId : minionIds) {
            PreparedStatement updateStatement = connection.prepareStatement(MinionAgeIncreaserStatements.UPDATE_AGE_AND_NAME_TO_LOWER_CASE_MINION_BY_ID);
            updateStatement.setInt(1, minionId);
            updateStatement.executeUpdate();
        }
    }

    private static List<Integer> readMinionIds() {
        Scanner scanner = new Scanner(System.in);

        return Arrays.stream(scanner.nextLine()
                        .split(" "))
        .map(Integer::parseInt)
        .toList();
    }

}
