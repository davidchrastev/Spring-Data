package controller;

import N02_get_villains_names.GetVillainsNames;
import N03_get_minion_names.GetMinionNames;
import N04_add_minions.AddMinion;
import N05_change_town_names_casing.TownNameModifier;
import N06_remove_villain.VillainRemover;
import N07_print_all_minions_names.PrintAllMinionsNames;
import N08_increase_minions_age.MinionAgeIncreaser01;
import N09_increase_age_stored_procedure.MinionAgeIncreaser02;

import java.sql.SQLException;
import java.util.Scanner;

public class TaskController {
    private static final String START_EXERCISE = "Pick a number between 2-9 to start particular task";
    private static final String ERROR_MESSAGE = "ERROR";

    public static void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(START_EXERCISE);
        int choice = scanner.nextInt();

        switch (choice) {
            case 2 -> GetVillainsNames.startSecondTask();
            case 3 -> GetMinionNames.startThirdTask();
            case 4 -> AddMinion.startFourthTask();
            case 5 -> TownNameModifier.startFifthTask();
            case 6 -> VillainRemover.startSixthTask();
            case 7 -> PrintAllMinionsNames.startSeventhTask();
            case 8 -> MinionAgeIncreaser01.startEighthTask();
            case 9 -> MinionAgeIncreaser02.startLastTask();
            default -> System.out.println(ERROR_MESSAGE);
        }
    }
}
