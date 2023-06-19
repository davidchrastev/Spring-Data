package N04_add_minions;

public class PreparedStatements {

    public final static String SELECT_VILLAIN_BY_NAME = "SELECT id" +
            " FROM villains" +
            " WHERE name = ?;";

    public final static String INSERT_VILLAIN = "INSERT INTO villains(name, evilness_factor)" +
            " VALUE (?, 'evil');";

    public final static String SELECT_TOWN_BY_NAME = "SELECT " +
            " * " +
            " FROM towns " +
            " WHERE name = ?;";

    public final static String INSERT_TOWN = "INSERT INTO towns (name) VALUES (?)";

    public final static String INSERT_MINION = "INSERT INTO minions (name, age)\n" +
            "VALUE (?, ?);";

    public final static String ADD_MINION_TO_VILLAIN = "INSERT INTO minions_villains(minion_id, villain_id)\n" +
            "VALUE (?, ?);";

    public final static String SELECT_MINION_BY_NAME = "SELECT \n" +
            "    id\n" +
            "FROM minions\n" +
            "WHERE name = ?;";
}
