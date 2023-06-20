package N06_remove_villain;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class VillainRemover {
    public static void main(String[] args) {
        System.out.println(VillainRemoverPrintConstants.ENTER_VILLAIN_ID);

        int villainId = readVillainID();

        String villainAndCountMinions = deleteVillainAndMinions(villainId);

        printResult(villainAndCountMinions);
    }

    private static void printResult(String villainAndCountMinions) {
        if (villainAndCountMinions == null) {
            System.out.println(VillainRemoverPrintConstants.NO_VILLAIN_FOUND);
        } else {
            String[] parts = villainAndCountMinions.split(" ");
            String villainName = parts[0];
            String countMinions = parts[1];

            System.out.printf(VillainRemoverPrintConstants.VILLAIN_DELETED, villainName);
            System.out.printf(VillainRemoverPrintConstants.COUNT_MINIONS_DELETED, countMinions);
        }
    }

    private static int readVillainID() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String deleteVillainAndMinions(int villainId) {
        String villainName = null;
        int numMinionsReleased = 0;

        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(MinionStatements.SELECT_VILLAIN_BY_ID);
            selectStatement.setInt(1, villainId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                villainName = resultSet.getString("name");
                numMinionsReleased = releaseMinions(villainId);

                PreparedStatement deleteVillainStatement = connection.prepareStatement(MinionStatements.DELETE_VILLAIN_BY_ID);
                deleteVillainStatement.setInt(1, villainId);
                deleteVillainStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villainName + " " + numMinionsReleased;
    }

    public static int releaseMinions(int villainId) {
        int numMinionsReleased = 0;

        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement deleteStatement = connection.prepareStatement(MinionStatements.DELETE_VILLAIN_MINIONS);
            deleteStatement.setInt(1, villainId);
            numMinionsReleased = deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numMinionsReleased;
    }
}
