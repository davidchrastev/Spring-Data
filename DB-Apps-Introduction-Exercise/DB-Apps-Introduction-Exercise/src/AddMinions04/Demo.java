package AddMinions04;

import java.sql.*;

public class Demo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String minionName = "Robert";
            int minionAge = 14;
            String townName = "Berlin";
            String villainName = "Gru";

            // Check if the villain is present in the database
            if (!isVillainPresent(connection, villainName)) {
                // If the villain is not present, add them to the database
                addVillain(connection, villainName);
                System.out.println("Villain " + villainName + " was added to the database.");
            }

            // Check if the town is present in the database
            if (!isTownPresent(connection, townName)) {
                // If the town is not present, add it to the database
                addTown(connection, townName);
                System.out.println("Town " + townName + " was added to the database.");
            }

            // Add the minion to be a servant of the villain
            addMinion(connection, minionName, minionAge, townName, villainName);
            System.out.println("Successfully added " + minionName + " to be a minion of " + villainName + ".");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isVillainPresent(Connection connection, String villainName) throws SQLException {
        String query = "SELECT COUNT(*) FROM villains WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, villainName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private static void addVillain(Connection connection, String villainName) throws SQLException {
        String insertQuery = "INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil')";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, villainName);
            statement.executeUpdate();
        }
    }

    private static boolean isTownPresent(Connection connection, String townName) throws SQLException {
        String query = "SELECT COUNT(*) FROM towns WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, townName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private static void addTown(Connection connection, String townName) throws SQLException {
        String insertQuery = "INSERT INTO towns (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, townName);
            statement.executeUpdate();
        }
    }

    private static void addMinion(Connection connection, String minionName, int minionAge, String townName, String villainName) throws SQLException {
        String insertQuery = "INSERT INTO minions (name, age, town_id) " +
                "VALUES (?, ?, (SELECT id FROM towns WHERE name = ?))";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, minionName);
            statement.setInt(2, minionAge);
            statement.setString(3, townName);
            statement.executeUpdate();
        }

        String assignQuery = "UPDATE minions SET villain_id = (SELECT id FROM villains WHERE name = ?) " +
                "WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(assignQuery)) {
            statement.setString(1, villainName);
            statement.setString(2, minionName);
            statement.executeUpdate();
        }
    }
}

