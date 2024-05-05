package session10.view;

import session10.controller.CustomerController;
import session10.controller.ProductController;
import session10.entity.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsoleUI {
    private final BufferedReader reader;
    CustomerController cusController = new CustomerController();
    ProductController productController = new ProductController();

    public ConsoleUI() throws SQLException {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }



    private int menuCustomer() throws IOException {
        System.out.println("=====================");
        System.out.println("1. Add customer");
        System.out.println("2. Update customer");
        System.out.println("3. Delete customer");
        System.out.println("4. Get all customer");
        System.out.println("5. Get customer by id");
        System.out.println("6. Get customer by name");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(reader.readLine());
        return choice;
    }

    private void getAllProducts() throws SQLException {
        ArrayList<Product> products = productController.getAllProducts();

        //lambda expression
        //nếu chỉ 1 biến có thể bỏ bớt ngoặc tròn
        products.forEach((product) ->{
            System.out.println("====================");
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("Product Description: " + product.getDescription());
        });
    }

    private void addProduct() throws SQLException, IOException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter Product Name: ");
        String name = reader.readLine();
        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(reader.readLine());
        System.out.print("Enter Product Description: ");
        String description = String.valueOf(reader.read());
        Product product = new Product(id, name, price, description);
        productController.addProduct(product);
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

    private void choice1() throws IOException, SQLException {
        while (true) {
            int choice = menuCustomer();
            switch (choice) {
                case 1:
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
                    reader.close();
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private int menuProduct() throws IOException {
        System.out.println("=================");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Get All Product");
        System.out.println("5. Get Product By ID");
        System.out.println("6. Get Product By Name");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(reader.readLine());;
        return choice ;
    }

    private void choice2() throws IOException, SQLException {
        while (true) {
            int choice = menuProduct();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    getAllProducts();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    reader.close();
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
                    choice2();
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
                    reader.close();
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
                    reader.close();
                    System.exit(0);
                }
            }
        }
    }
}