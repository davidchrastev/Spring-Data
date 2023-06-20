package N09_increase_age_stored_procedure;

public class MinionAgeIncreaser02Statements {

    public static final String CREATE_PROCEDURE_UPDATE_MINIONS_AGE = "CREATE PROCEDURE usp_get_older(IN minionId INT)\n" +
            "BEGIN\n" +
            "    UPDATE minions SET age = age + 1 WHERE id = minionId;\n" +
            "END";

    public static final String SELECT_NAME_AGE_MINION =  "SELECT name, age FROM minions WHERE id = ?";

    public static final String CALL_PROCEDURE = "CALL usp_get_older(?)";
}
