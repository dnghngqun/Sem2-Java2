package PracticalTest;

import PracticalTest.View.View;

import java.io.IOException;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException, IOException {
        View view = new View();
        view.start();
    }
}
