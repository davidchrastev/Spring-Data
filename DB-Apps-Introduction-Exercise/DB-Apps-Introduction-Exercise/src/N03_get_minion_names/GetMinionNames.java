package N03_get_minion_names;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class GetMinionNames {
    public static void startThirdTask() throws SQLException {
        int id = readInput();

        Connection connection = ConnectionSQL.setUpConnection();

        PreparedStatement selectNameByID = getStatementSelectNameByID(id, connection);

        PreparedStatement selectNameAndAgeByID = getStatementSelectNameAndAgeByID(id, connection);

        ResultSet resultSet = selectNameByID.executeQuery();

        printOutput(id, selectNameAndAgeByID, resultSet);

        connection.close();
    }

    private static void printOutput(int id, PreparedStatement secondStatement, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String villainName = resultSet.getString(1);
            System.out.printf(PrintPatterns.PRINT_VILLAIN_PATTERN, villainName);
            resultSet = secondStatement.executeQuery();
            int counter = 1;
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int age = resultSet.getInt(2);
                System.out.printf(PrintPatterns.PRINT_MINION_PATTERN, counter, name, age);
                counter++;
            }
        } else {
            System.out.printf(PrintPatterns.NO_VILLAIN_WITH_GIVEN_ID, id);
        }
    }

    private static int readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(PrintPatterns.ENTER_VILLAIN_ID);
        return Integer.parseInt(scanner.nextLine());
    }

    private static PreparedStatement getStatementSelectNameAndAgeByID(int id, Connection connection) throws SQLException {
        PreparedStatement secondStatement =
                connection.prepareStatement(PrepareStatementsConstants.SELECT_NAME_AGE_BY_VILLAIN_ID);
        secondStatement.setInt(1, id);
        return secondStatement;
    }

    private static PreparedStatement getStatementSelectNameByID(int id, Connection connection) throws SQLException {
        PreparedStatement firstStatement =
                connection.prepareStatement(PrepareStatementsConstants.SELECT_NAME_BY_ID);
        firstStatement.setInt(1, id);
        return firstStatement;
    }

}
