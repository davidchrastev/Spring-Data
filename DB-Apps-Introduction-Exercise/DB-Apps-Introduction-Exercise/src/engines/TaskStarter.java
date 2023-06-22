package engines;

import controller.TaskController;

import java.sql.SQLException;

public class TaskStarter {

    public static void main(String[] args) throws SQLException {
        TaskController.start();
    }
}
