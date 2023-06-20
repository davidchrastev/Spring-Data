package N09_increase_age_stored_procedure;


import N04_add_minions.ConnectionSQL;

import java.sql.*;

public class StoredProcedureCreator {

    public static void createProcedureUpdateMinionsAge() {
        try (Connection connection = ConnectionSQL.setUpConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate(MinionAgeIncreaser02Statements.CREATE_PROCEDURE_UPDATE_MINIONS_AGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkStoredProcedureExists(String procedureName) {
        try (Connection connection = ConnectionSQL.setUpConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getProcedures(null, null, procedureName);

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
