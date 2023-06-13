import com.mysql.cj.xdevapi.Result;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BasicRetrieveProgram {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions", properties);

        String name = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());

        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM minions WHERE name = ? AND age > ?");

        statement.setString(1, name);
        statement.setInt(2, age);


        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString("name") + " " + rs.getString("age"));
        }





    }
}
