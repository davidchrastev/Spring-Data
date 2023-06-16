import AddMinions04.ConnectionSQL;
import AddMinions04.PreparedStatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionsNames {

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.setUpConnection();

        List<String> minionsNames = new ArrayList<>();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT name FROM minions");

        ResultSet result = preparedStatement.executeQuery();


        while (result.next()) {
            minionsNames.add(result.getString("name"));
        }

        //Print
        //first + 1, last - 1, first + 2, last - 2 ....

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
