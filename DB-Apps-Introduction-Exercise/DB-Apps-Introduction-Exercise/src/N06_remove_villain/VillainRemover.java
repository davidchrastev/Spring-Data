package N06_remove_villain;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VillainRemover {
    public static void main(String[] args) {
        System.out.println(VillainRemoverPrintConstants.ENTER_VILLAIN_ID);

        int villainId = readVillainID();

        String result = deleteVillainAndHisMinions(villainId);

        printResult(result);
    }

    private static void printResult(String villainName) {
        if (villainName == null) {
            System.out.println("No such villain was found.");
        } else {
            List<String> result = Arrays.stream(villainName.split(" ")).toList();

            System.out.println(result.get(0) + " was deleted");
            System.out.println(result.get(1) + " minions released");
        }
    }

    private static int readVillainID() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String deleteVillainAndHisMinions(int villainId) {
        String villainName = null;
        int releasedMinions = 0;

        try (Connection connection = ConnectionSQL.setUpConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement(MinionStatements.SELECT_VILLAIN_BY_ID);
            selectStatement.setInt(1, villainId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                villainName = resultSet.getString("name");

                releasedMinions = releaseMinions(villainId);

                PreparedStatement deleteVillainStatement = connection.prepareStatement(MinionStatements.DELETE_VILLAIN_BY_ID);
                deleteVillainStatement.setInt(1, villainId);
                deleteVillainStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villainName + " " + releasedMinions;
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