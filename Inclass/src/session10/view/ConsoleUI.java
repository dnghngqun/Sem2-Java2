package session10.view;

import session10.controller.CustomerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ConsoleUI {
    private final BufferedReader reader;
    CustomerController cusController = new CustomerController();

    public ConsoleUI() throws SQLException {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void addCustomer() throws IOException, SQLException {
        System.out.print("Enter customer id: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter customer name: ");
        String name = reader.readLine();
        System.out.print("Enter customer address: ");
        String address = reader.readLine();
        System.out.print("Enter customer email: ");
        String email = reader.readLine();
        cusController.addCustomer(id, name, address, email);
    }

    private void updateCustomer() throws IOException, SQLException {
        System.out.print("Enter customer id: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter customer name: ");
        String name = reader.readLine();
        System.out.print("Enter customer address: ");
        String address = reader.readLine();
        System.out.print("Enter customer email: ");
        String email = reader.readLine();
        cusController.updateCustomer(id, name, address, email);
    }

    private void deleteCustomer() throws IOException, SQLException {
        System.out.print("Enter customer id: ");
        int id = Integer.parseInt(reader.readLine());
        cusController.removeCustomer(id);
    }

    private int menuCustomer() throws IOException {
        System.out.println("1. Add customer");
        System.out.println("2. Update customer");
        System.out.println("3. Delete customer");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(reader.readLine());
        return choice;
    }

    private int menu() throws IOException {
        System.out.println("=======================");
        System.out.println("1. Add, Update, Remove Customer");
        System.out.println("2. Add, Update, Remove Employee");
        System.out.println("3. Create, Update, Remove Order");
        System.out.println("4. Displays a list of orders for each customer");
        System.out.println("5. Calculate the total value of each order");
        System.out.println("6. Update status for orders");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(reader.readLine());
        return choice;
    }

    public void choice1() throws IOException, SQLException {
        while (true) {
            int choice = menuCustomer();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    public void start() throws IOException, SQLException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1:
                    choice1();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
            System.out.println("Do you want to continue? ");
            System.out.print("Press any key to continue or N to exit: ");
            String line = reader.readLine();
            if (!line.isEmpty()) {
                char enter = line.charAt(0);
                if (enter == 'N' || enter == 'n') {
                    System.exit(0);
                }
            }
        }
    }
}