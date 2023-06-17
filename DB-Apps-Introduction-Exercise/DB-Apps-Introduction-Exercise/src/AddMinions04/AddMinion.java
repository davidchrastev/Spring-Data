package AddMinions04;

import java.sql.*;
import java.util.List;

public class AddMinion {
    public static void main(String[] args) throws SQLException {
        startProgram();
    }

    private static void startProgram() throws SQLException {
        Connection connectionSQL = ConnectionSQL.setUpConnection();

        String input = Reader.read();

        List<String> minionProperties = Reader.getMinionProperties(input);

        input = Reader.read();

        String townName = minionProperties.get(2);

        checkIfTownIsPresent(connectionSQL, townName);

        String villainName = Reader.getVillainName(input);

        checkIfVillainIsPresent(connectionSQL, villainName);

        String minionName = minionProperties.get(0);

        addMinionToDB(connectionSQL, minionProperties);

        int minionId = getMinionId(connectionSQL, minionName);
        int villainId = getVillainId(connectionSQL, villainName);

        addMinionToVillain(connectionSQL, villainName, minionName, minionId, villainId);

        connectionSQL.close();
    }

    private static void addMinionToVillain(Connection connectionSQL, String villainName, String minionName, int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connectionSQL.prepareStatement(PreparedStatements.ADD_MINION_TO_VILLAIN);
        preparedStatement.setInt(1, minionId);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeUpdate();

        System.out.printf(Outputs.MINION_ADDED_TO_VILLAIN, minionName, villainName);
    }

    private static int getVillainId(Connection connectionSQL, String villainName) throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connectionSQL.prepareStatement(PreparedStatements.SELECT_VILLAIN_BY_NAME);
        preparedStatement.setString(1, villainName);
        resultSet = preparedStatement.executeQuery();

        int villainId;
        if (resultSet.next()) {
            villainId = resultSet.getInt(1);
        } else {
            throw new SQLException("Failed to retrieve villain ID.");
        }
        return villainId;
    }

    private static int getMinionId(Connection connectionSQL, String minionName) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement =
                connectionSQL.prepareStatement(PreparedStatements.SELECT_MINION_BY_NAME);
        preparedStatement.setString(1, minionName);

        ResultSet resultSet = preparedStatement.executeQuery();

        int minionId;
        if (resultSet.next()) {
            minionId = resultSet.getInt(1);
        } else {
            throw new SQLException("Failed to retrieve minion ID.");
        }
        return minionId;
    }

    private static void addMinionToDB(Connection connectionSQL, List<String> minionProperties) throws SQLException {
        String minionName = minionProperties.get(0);
        int age = Integer.parseInt(minionProperties.get(1));

        PreparedStatement preparedStatement =
                connectionSQL.prepareStatement(PreparedStatements.INSERT_MINION);
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, age);
        preparedStatement.executeUpdate();
    }

    private static void checkIfTownIsPresent(Connection connectionSQL, String townName) throws SQLException {
        PreparedStatement preparedStatement =
                connectionSQL.prepareStatement(PreparedStatements.SELECT_TOWN_BY_NAME);
        preparedStatement.setString(1, townName);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            addTown(connectionSQL, townName);
        }
    }

    private static void addTown(Connection connectionSQL, String townName) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connectionSQL.prepareStatement(PreparedStatements.INSERT_TOWN);
        preparedStatement.setString(1, townName);
        preparedStatement.executeUpdate();
        System.out.printf(Outputs.TOWN_ADDED, townName);
        System.out.println();
    }

    private static void checkIfVillainIsPresent(Connection connectionSQL, String villainName) throws SQLException {
        PreparedStatement preparedStatement =
                connectionSQL.prepareStatement(PreparedStatements.SELECT_VILLAIN_BY_NAME);
        preparedStatement.setString(1, villainName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            addVillain(connectionSQL, villainName);
        }
    }

    private static void addVillain(Connection connectionSQL, String villainName) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connectionSQL.prepareStatement(PreparedStatements.INSERT_VILLAIN);
        preparedStatement.setString(1, villainName);
        preparedStatement.executeUpdate();
        System.out.printf(Outputs.VILLAIN_ADDED, villainName);
        System.out.println();
    }

}
