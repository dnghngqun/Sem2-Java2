package session10;

import session10.view.ConsoleUI;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException, IOException {
        ConsoleUI ui = new ConsoleUI();
        ui.start();
    }
}
