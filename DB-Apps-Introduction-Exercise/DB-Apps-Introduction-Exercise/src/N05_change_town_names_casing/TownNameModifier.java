package N05_change_town_names_casing;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TownNameModifier {

    public static void startFifthTask() {
        String country = readCountry();

        List<String> changedTowns = changeTownNames(country);

        printResult(changedTowns);
    }

    private static void printResult(List<String> changedTowns) {
        if (changedTowns.isEmpty()) {
            System.out.println(PrintConstants.NO_TOWN_NAMES_AFFECTED);
        } else {
            System.out.printf(PrintConstants.COUNT_TOWN_NAMES_AFFECTED, changedTowns.size(), (changedTowns.size() > 1) ? "s" : "");
            System.out.print("[" + (String.join(", ", changedTowns)) + "]");
        }
    }

    private static String readCountry() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<String> changeTownNames(String country) {
        List<String> changedTowns = new ArrayList<>();

        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement statement = connection.prepareStatement(TownStatements.UPDATE_TO_UPPER_CASE);
            statement.setString(1, country);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                PreparedStatement selectStatement = connection.prepareStatement(TownStatements.SELECT_NAME);
                selectStatement.setString(1, country);
                ResultSet resultSet = selectStatement.executeQuery();

                while (resultSet.next()) {
                    changedTowns.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return changedTowns;
    }
}

