package N02_get_villains_names;

import N04_add_minions.ConnectionSQL;

import java.sql.*;
public class GetVillainsNames {

    private static final String SELECT_VILLAIN_NAME_COUNT_MINIONS = "SELECT" +
            "    CONCAT_WS(' ', v.name, COUNT(mv.minion_id)) AS Output\n" +
            "FROM villains v\n" +
            "JOIN minions_villains mv on v.id = mv.villain_id\n" +
            "GROUP BY v.name\n" +
            "HAVING COUNT(mv.minion_id) > 15\n" +
            "ORDER BY COUNT(mv.minion_id) DESC";
    public static void startSecondTask() throws SQLException {
        Connection connection = ConnectionSQL.setUpConnection();

        PreparedStatement statement = connection.prepareStatement(SELECT_VILLAIN_NAME_COUNT_MINIONS);

        printResult(statement);

        connection.close();
    }

    private static void printResult(PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            System.out.println(result.getString("Output"));
        }
    }
}
