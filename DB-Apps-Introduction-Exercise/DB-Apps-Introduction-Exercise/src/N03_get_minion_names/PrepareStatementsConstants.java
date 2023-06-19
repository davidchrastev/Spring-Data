package N03_get_minion_names;

public class PrepareStatementsConstants {
    public static final String SELECT_NAME_BY_ID = "SELECT\n" +
            "    name\n" +
            "FROM villains\n" +
            "WHERE id = ?";
    public static final String SELECT_NAME_AGE_BY_VILLAIN_ID = "SELECT\n" +
            "    m.name as name,\n" +
            "    m.age as age\n" +
            "FROM villains v\n" +
            "JOIN minions_villains mv on v.id = mv.villain_id\n" +
            "JOIN minions m on m.id = mv.minion_id\n" +
            "WHERE mv.villain_id = ?";
}
