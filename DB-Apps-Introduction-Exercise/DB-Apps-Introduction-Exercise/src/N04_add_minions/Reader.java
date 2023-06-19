package N04_add_minions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private String villainName;
    private List<String> minionProperties;
    private final static Scanner scanner = new Scanner(System.in);


    public static String getVillainName(String input) {
        return input.split(" ")[1];
    }

    public static List<String> getMinionProperties(String input) {
        List<String> result = new ArrayList<>(List.of(input.split(" ")));
        result.remove(0);

        return result;
    }

    public static String read() {
        return scanner.nextLine();
    }
}
