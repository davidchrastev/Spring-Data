package N06_remove_villain;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class VillainRemover {
    public static void main(String[] args) {
        int villainId = readVillainID();

        String villainName = deleteVillain(villainId);

        printResult(villainName);
    }

    private static int readVillainID() {
        System.out.println(VillainRemoverPrintConstants.ENTER_VILLAIN_ID);
        try {
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid villain ID.");
            return readVillainID();
        }
    }

    private static void printResult(String villainName) {
        if (villainName == null) {
            System.out.println("No such villain was found.");
        } else {
            int numMinionsReleased = releaseMinions(villainName);
            System.out.println(villainName + " was deleted");
            System.out.println(numMinionsReleased + " minions released");
        }
    }

    public static String deleteVillain(int villainId) {
        String villainName = null;

        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(MinionStatements.SELECT_VILLAIN_BY_ID);
            selectStatement.setInt(1, villainId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                villainName = resultSet.getString("name");

                PreparedStatement deleteMinionsStatement = connection.prepareStatement(MinionStatements.DELETE_VILLAIN_MINIONS);
                deleteMinionsStatement.setInt(1, villainId);
                deleteMinionsStatement.executeUpdate();

                PreparedStatement deleteVillainStatement = connection.prepareStatement(MinionStatements.DELETE_VILLAIN_BY_ID);
                deleteVillainStatement.setInt(1, villainId);
                deleteVillainStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villainName;
    }

    public static int releaseMinions(String villainName) {
        int numMinionsReleased = 0;

        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(MinionStatements.SELECT_MINIONS_BY_VILLAIN);
            selectStatement.setString(1, villainName);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                numMinionsReleased++;

                int minionId = resultSet.getInt("id");
                PreparedStatement updateStatement = connection.prepareStatement(MinionStatements.UPDATE_MINION_VILLAIN_NULL);
                updateStatement.setInt(1, minionId);
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numMinionsReleased;
    }
}
