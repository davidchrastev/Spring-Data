package N05_print_all_minions_names;

import N04_add_minions.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionsNames {

    public static void main(String[] args) throws SQLException {
        Connection connection = setUpConnection();

        List<String> minionsNames = new ArrayList<>();

        ResultSet result = prepareAndExecute(connection);

        getMinionsNames(minionsNames, result);

        //Print
        //first + 1, last - 1, first + 2, last - 2 ....

        printMinions(minionsNames);

    }

    private static Connection setUpConnection() throws SQLException {
        return ConnectionSQL.setUpConnection();
    }

    private static ResultSet prepareAndExecute(Connection connection) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT name FROM minions");

        return preparedStatement.executeQuery();
    }

    private static void getMinionsNames(List<String> minionsNames, ResultSet result) throws SQLException {
        while (result.next()) {
            minionsNames.add(result.getString("name"));
        }
    }

    private static void printMinions(List<String> minionsNames) {
        int front = 0;
        int rear = minionsNames.size() - 1;
        int counter = 0;

        while (front <= rear) {

            if (counter % 2 != 0) {
                System.out.println(rear + " " + minionsNames.get(rear) + " last");
                rear--;
            } else {
                System.out.println(front + " " + minionsNames.get(front) + " first");
                front++;
            }

            counter++;
        }
    }
}
