package session9.view;

import session9.controller.CustomerController;
import session9.controller.LoginController;
import session9.entity.Customers;
import session9.entity.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginConsoleUI {
    private final BufferedReader sc;
    //step 1
    LoginController loginController = new LoginController();
    CustomerController cusController = new CustomerController();

    public LoginConsoleUI(Scanner sc) throws SQLException {
        this.sc = new BufferedReader(new InputStreamReader(System.in));
    }

    Users users = new Users();

    public LoginConsoleUI() throws SQLException {

        this.sc = new BufferedReader(new InputStreamReader(System.in));
    }


    private int menu(){
        System.out.println("=======Admin zone=======");
        System.out.println("1. Login with statement");
        System.out.println("2. Login with prepared statement");
        System.out.println("3. Find customer by id");
        System.out.println("4. Get all customer");
        System.out.println("5. Add customer");
        System.out.println("6. Delete customer");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return choice;
    }
    private  void loginStatementUI() throws SQLException, IOException {
        System.out.print("Enter username: ");
        String username = sc.readLine();
        System.out.print("Enter password: ");
        String password = sc.readLine();
        users.setUsername(username);
        users.setPassword(password);
        //step 1
        String result = loginController.loginStatementController(users);
        //step 5
        System.out.println(result);
    }
   private void findCustomerById() throws SQLException, IOException {
       System.out.print("Enter customer id: ");
       int id = Integer.parseInt(sc.readLine());
       Customers result = cusController.findCustomerById(id);
       String message = result.getCustomer_id() + " " + result.getFirst_name() + " " + result.getLast_name() + " " + result.getEmail();
       System.out.println(message);
   }
    private void getAllCustomers() throws SQLException {
        ArrayList<Customers> customers = cusController.getAllCustomers();
        for(Customers customer : customers){
            System.out.println(customer.getCustomer_id() + " " + customer.getFirst_name() + " " + customer.getLast_name() + " " + customer.getEmail());
        }
    }

    private void deleteCustomer() throws SQLException, IOException {
        System.out.print("Enter customer id: ");
        int id = Integer.parseInt(sc.readLine());
        cusController.removeCustomer(id);
        System.out.println("Remove success!");
    }

    private void loginPreparedUI() throws SQLException, IOException {
        System.out.print("Enter username: ");
        String username = sc.readLine();
        System.out.print("Enter password: ");
        String password = sc.readLine();
        users.setUsername(username);
        users.setPassword(password);
        //step 1
        String result = loginController.loginPreparedStatementController(users);
        //step5
        System.out.println(result);
    }

    private  void addCustomer() throws SQLException, IOException {
        System.out.print("Enter customer id: ");
        int id = Integer.parseInt(sc.readLine());
        System.out.print("Enter first name: ");
        String firstName = sc.readLine();
        System.out.print("Enter last name: ");
        String lastName = sc.readLine();
        System.out.print("Enter email: ");
        String email = sc.readLine();
        cusController.addCustomer(id, firstName, lastName, email);
        System.out.println("Add success");
    }
    public void start() throws SQLException, IOException {
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
                case 3:
                   findCustomerById();
                   break;
                case 4:
                    getAllCustomers();
                    break;
                case 5:
                    addCustomer();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
            System.out.println("Do you want to continue? ");
            System.out.print("Press any key to continue or N to exit: ");
            String line = sc.readLine();
            if(!line.isEmpty()){
                char enter = line.charAt(0);
                if(enter == 'n' || enter == 'N'){
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                }
            }
        }
    }

}
