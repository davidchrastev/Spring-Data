package N08_increase_minions_age;

public class MinionAgeIncreaserStatements {

    public static final String UPDATE_AGE_AND_NAME_TO_LOWER_CASE_MINION_BY_ID = "UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id = ?";

    public static final String SELECT_NAME_AND_AGE_FROM_MINIONS = "SELECT name, age FROM minions";
}
