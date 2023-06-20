package N06_remove_villain;

public class MinionStatements {
    public static final String SELECT_VILLAIN_BY_ID = "SELECT name FROM villains WHERE id = ?";
    public static final String DELETE_VILLAIN_BY_ID = "DELETE FROM villains WHERE id = ?";
    public static final String DELETE_VILLAIN_MINIONS = "DELETE FROM minions_villains WHERE villain_id = ?";
}
