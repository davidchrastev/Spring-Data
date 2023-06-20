package N09_increase_age_stored_procedure;
import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

import static N09_increase_age_stored_procedure.MinionAgeIncreaser02Statements.*;

public class MinionAgeIncreaser02 {
    private static final String MINION_DETAILS = "Minion Details:";
    private static final String MINION_NAME_PATTERN = "Name: %s\n";
    private static final String MINION_AGE_PATTERN = "Age: %d\n";
    private static final String NO_MINION_WITH_GIVEN_ID = "No minion found with the provided ID.";
    private static final String PROCEDURE_NAME = "usp_get_older";

    public static void main(String[] args) {

        if (!StoredProcedureCreator.checkStoredProcedureExists(PROCEDURE_NAME)) {
            StoredProcedureCreator.createProcedureUpdateMinionsAge();
        }

        int minionId = readMinionIdFromConsole();

        increaseMinionAge(minionId);

        printMinionDetails(minionId);

    }
    private static int readMinionIdFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the minion ID:");
        return scanner.nextInt();
    }

    private static void increaseMinionAge(int minionId) {
        try (Connection connection = ConnectionSQL.setUpConnection()) {
            CallableStatement callableStatement = connection.prepareCall(CALL_PROCEDURE);
            callableStatement.setInt(1, minionId);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printMinionDetails(int minionId) {
        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_NAME_AGE_MINION);
            selectStatement.setInt(1, minionId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println(MINION_DETAILS);
                System.out.printf(MINION_NAME_PATTERN, name);
                System.out.printf(MINION_AGE_PATTERN, age);
            } else {
                System.out.println(NO_MINION_WITH_GIVEN_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
