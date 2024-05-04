package session9;

import session9.view.LoginConsoleUI;

import java.io.IOException;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException, IOException {
        LoginConsoleUI ui = new LoginConsoleUI();
        ui.start();
    }
}
