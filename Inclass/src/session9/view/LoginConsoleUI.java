package session9.view;

import session9.controller.LoginController;
import session9.entity.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginConsoleUI {
    private final Scanner sc;
    //step 1
    LoginController loginController = new LoginController();

    public LoginConsoleUI(Scanner sc) throws SQLException {
        this.sc = new Scanner(System.in);
    }

    Users users = new Users();

    public LoginConsoleUI() throws SQLException {

        this.sc = new Scanner(System.in);
    }


    private int menu(){
        System.out.println("=======Admin zone=======");
        System.out.println("1. Login with statement");
        System.out.println("2. Login with prepared statement");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }
    private  void loginStatementUI() throws SQLException {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.setUsername(username);
        users.setPassword(password);
        //step 1
        String result = loginController.loginStatementController(users);
        //step 5
        System.out.println(result);
    }

    private void loginPreparedUI() throws SQLException {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.setUsername(username);
        users.setPassword(password);
        //step 1
        String result = loginController.loginPreparedStatementController(users);
        //step5
        System.out.println(result);
    }

    public void start() throws SQLException {
        while (true){
            int choice = menu();
            switch (choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    loginStatementUI();
                    break;
                case 2:
                    loginPreparedUI();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);

            }
        }
    }

}
