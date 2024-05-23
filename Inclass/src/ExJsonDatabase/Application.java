package ExJsonDatabase;

import ExJsonDatabase.view.ConsoleUI;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException, IOException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        ConsoleUI ui = new ConsoleUI();
        ui.start();

    }
}
