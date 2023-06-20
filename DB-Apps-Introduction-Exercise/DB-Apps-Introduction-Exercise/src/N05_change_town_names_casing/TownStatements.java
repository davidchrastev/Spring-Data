package N05_change_town_names_casing;

public class TownStatements {
    public static final String UPDATE_TO_UPPER_CASE = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
    public static final String SELECT_NAME = "SELECT name FROM towns WHERE country = ?";
}
