package N06_remove_villain;

public class MinionStatements {
    public static final String SELECT_VILLAIN_BY_ID = "SELECT name FROM villains WHERE id = ?";
    public static final String DELETE_VILLAIN_BY_ID = "DELETE FROM villains WHERE id = ?";
    public static final String DELETE_VILLAIN_MINIONS = "DELETE FROM minions_villains WHERE villain_id = ?";
    public static final String SELECT_MINIONS_BY_VILLAIN = "SELECT id FROM minions WHERE villain_id = (SELECT id FROM villains WHERE name = ?)";
    public static final String UPDATE_MINION_VILLAIN_NULL = "UPDATE minions SET villain_id = NULL WHERE id = ?\n";
}
