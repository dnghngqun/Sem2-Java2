package session10.view;

import session10.controller.CustomerController;
import session10.controller.OrderManagerController;
import session10.controller.ProductController;
import session10.entity.Customer;
import session10.entity.Order;
import session10.entity.OrderDetail;
import session10.entity.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUI {
    private final BufferedReader reader;
    CustomerController cusController = new CustomerController();
    ProductController productController = new ProductController();
//    OrderController orderController = new OrderController();
    OrderManagerController orderManager = new OrderManagerController();
    public ConsoleUI() throws SQLException {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

//    Customer
    private void addCustomer() throws SQLException, IOException {
        System.out.print("Enter Customer Name: ");
        String name = reader.readLine();
        System.out.print("Enter Customer Address: ");
        String address = reader.readLine();
        System.out.print("Enter Customer Email: ");
        String email = reader.readLine();
        Customer customer = new Customer( name, address, email);
        cusController.addCustomer(customer);
    }

    private void removeCustomer() throws SQLException, IOException {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(reader.readLine());
        cusController.removeCustomer(id);
    }

    private void updateCustomer() throws SQLException, IOException {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter Customer Name: ");
        String name = reader.readLine();
        System.out.print("Enter Customer Address: ");
        String address = reader.readLine();
        System.out.print("Enter Customer Email: ");
        String email = reader.readLine();
        Customer customer = new Customer(name, address, email);
        cusController.updateCustomer(id, customer);
    }

    private void getAllCustomers() throws SQLException {
        ArrayList<Customer> customers = cusController.getAllCustomers();
        for (Customer c: customers){
            System.out.println("==================");
            System.out.println("Customer ID: " + c.getId());
            System.out.println("Customer Name: " + c.getName());
            System.out.println("Customer Address: " + c.getAddress());
            System.out.println("Customer Email: " + c.getEmail());
        }
    }

    private void getCustomerById() throws SQLException, IOException {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(reader.readLine());
        Customer customer = cusController.getCustomerById(id);
        System.out.println("=================");
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Customer Address: " + customer.getAddress());
        System.out.println("Customer Email: " + customer.getEmail());
    }

    private void getCustomerByName() throws SQLException, IOException {
        System.out.print("Enter Customer Name: ");
        String name = reader.readLine();
        ArrayList<Customer> customers = cusController.getCustomersByName(name);
        for (Customer c: customers){
            System.out.println("Customer ID: " + c.getId());
            System.out.println("Customer Name: " + c.getName());
            System.out.println("Customer Address: " + c.getAddress());
            System.out.println("Customer Email: " + c.getEmail());
        }
    }

    private int menuCustomer() throws IOException {
        System.out.println("=========MENU CUSTOMER============");
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

//    Order
    private int menuOrder() throws IOException {
        System.out.println("=======MENU ORDER==============");
        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Delete Order");
        System.out.println("4. Get all Order");
        System.out.println("5. Get Order by id");
        System.out.println("6. Get Order by customer ID");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(reader.readLine());
        return choice;
    }

    private void choice3() throws IOException, SQLException {
        while (true) {
            int choice = menuOrder();
            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    updateOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    getAllOrders();
                    break;
                case 5:
                    getOrderById();
                    break;
                case 6:
                    getOrderByCustomerId();
                    break;
                case 7:
                   start();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private void addOrder() throws SQLException, IOException {

        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(reader.readLine());
        Order order = new Order(customerId);
        int i = 0;
        ArrayList <OrderDetail> orderDetails = new ArrayList<>();
        while (true) {
            i++;
            System.out.println("Enter order details " + i);
            System.out.print("Enter Product ID: ");
            int productId = Integer.parseInt(reader.readLine());
            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(reader.readLine());
            System.out.print("Enter unit price of product: ");
            double unitPrice = Double.parseDouble(reader.readLine());
            System.out.print("Enter discount: ");
            double discount = Double.parseDouble(reader.readLine());
            OrderDetail orderDetail = new OrderDetail(productId, quantity, unitPrice, discount);
            orderDetails.add(orderDetail);

            System.out.println("Do you want to add more order details?");
            System.out.print("Press any key to continue or N to stop: ");
            String line = reader.readLine();
            if (!line.isEmpty()) {
                char enter = line.charAt(0);
                if (enter == 'N' || enter == 'n') {
                   break;

                }
            }
        }
        orderManager.addOrder(order, orderDetails);

    }

    private void updateOrder() throws SQLException, IOException{
        System.out.print("Enter Order ID: ");
        int OrderId = Integer.parseInt(reader.readLine());
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(reader.readLine());
        System.out.print("Enter total amount: ");
        double totalAmount = Double.parseDouble(reader.readLine());
        System.out.print("Enter Order Status (0: Cancelled 1: Pending 2: Completed): ");
        int status = Integer.parseInt(reader.readLine());
        Order order = new Order(OrderId, customerId, totalAmount, status);
        boolean result = orderManager.updateOrder(order);
        if(!result){
            System.out.println("Error updating order status");
            System.out.println("Please try again");
            updateOrder();
        }
    }

    private void updateOrderStatus() throws SQLException, IOException{

        System.out.print("Enter Order ID: ");
        int OrderId = Integer.parseInt(reader.readLine());
        System.out.print("Enter Order Status (0: Cancelled 1: Pending 2: Completed): ");
        int status = Integer.parseInt(reader.readLine());
        boolean result = orderManager.updateOrderStatus(OrderId, status);
        if(!result){
            System.out.println("Error updating order status");
            System.out.println("Please try again");
            updateOrderStatus();
        }
    }

    private void deleteOrder() throws SQLException, IOException{
        System.out.print("Enter Order ID: ");
        int OrderId = Integer.parseInt(reader.readLine());
        boolean result = orderManager.deleteOrder(OrderId);
        if(!result){
            System.out.println("Error deleting order");
            System.out.println("Please try again");
            deleteOrder();
        }
    }

    private void getAllOrders() throws SQLException {
        List<Order> orders = orderManager.getAllOrders();
        for (Order o: orders){
            System.out.println("Order ID: " + o.getId());
            System.out.println("Customer ID: " + o.getCustomerId());
            System.out.println("Total Amount: " + o.getTotalAmount());
            System.out.println("Order Status: " + o.getStatusString());
        }
    }

    private void getOrderById() throws SQLException, IOException {
        System.out.print("Enter Order ID: ");
        int OrderId = Integer.parseInt(reader.readLine());
        Order order = orderManager.getOrderById(OrderId);
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer ID: " + order.getCustomerId());
    }

    private void getOrderByCustomerId() throws SQLException, IOException {
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(reader.readLine());
        List<Order> orders = orderManager.getOrdersByCustomerId(customerId);
        for (Order o: orders){
            System.out.println("Order ID: " + o.getId());
            System.out.println("Customer ID: " + o.getCustomerId());
        }
    }



//    private void updateOrderStatus() throws SQLException, IOException{
//
//        System.out.print("Enter Order ID: ");
//        int OrderId = Integer.parseInt(reader.readLine());
//        System.out.print("Enter Order Status (0: Cancelled 1: Pending 2: Completed): ");
//        int status = Integer.parseInt(reader.readLine());
//        boolean result = orderController.updateOrderStatus(OrderId, status);
//        if(!result){
//            updateOrderStatus();
//        }
//    }
//
//    private void addOrder() throws SQLException, IOException {
//        System.out.println("Enter order ID: ");
//        int id = Integer.parseInt(reader.readLine());
//        System.out.println("Enter customer ID: ");
//        int customerId = Integer.parseInt(reader.readLine());
//        Order order = new Order(id, customerId);
//        orderController.addOrder(order);
//    }
//
//    private void updateOrder() throws SQLException, IOException {
//        System.out.println("Enter order ID to update: ");
//        int id = Integer.parseInt(reader.readLine());
//        System.out.println("Enter customer ID: ");
//        int customerId = Integer.parseInt(reader.readLine());
//        System.out.println("Enter total amount: ");
//        double totalAmount = Double.parseDouble(reader.readLine());
//        System.out.println("Enter order status (0: Cancelled 1: Pending 2: Completed): ");
//        int status = Integer.parseInt(reader.readLine());
//        Order order = new Order(id, customerId, totalAmount, status);
//        orderController.updateOrder(order);
//    }
//
//    private void deleteOrder() throws SQLException, IOException {
//        System.out.println("Enter order ID to delete: ");
//        int id = Integer.parseInt(reader.readLine());
//        orderController.deleteOrder(id);
//    }
//
//    private void  getAllOrders() throws SQLException {
//        ArrayList<Order> orders = orderController.getAllOrders();
//        for (Order o: orders){
//            System.out.println("===================");
//            System.out.println("Order ID: " + o.getId());
//            System.out.println("Customer ID: " + o.getCustomerId());
//            System.out.println("Customer Name: " + o.getCustomerName());
//            System.out.println("Order Date: " + o.getOrderDate());
//            System.out.println("Total Amount: " + o.getTotalAmount());
//            System.out.println("Order Status: " + o.getStatusString());
//        }
//    }
//
//    private void getOrderById() throws SQLException, IOException {
//        System.out.println("Enter order ID: ");
//        int id = Integer.parseInt(reader.readLine());
//        Order order = orderController.getOrderById(id);
//        System.out.println("===================");
//        System.out.println("Order ID: " + order.getId());
//        System.out.println("Customer ID: " + order.getCustomerId());
//        System.out.println("Customer Name: " + order.getCustomerName());
//        System.out.println("Order Date: " + order.getOrderDate());
//        System.out.println("Total Amount: " + order.getTotalAmount());
//        System.out.println("Order Status: " + order.getStatusString());
//    }
//
//    private void getOrderByCustomerId() throws SQLException, IOException {
//        System.out.println("Enter customer ID: ");
//        int id = Integer.parseInt(reader.readLine());
//        ArrayList<Order> orders = orderController.getOrdersByCustomerId(id);
//        for (Order o: orders){
//            System.out.println("===================");
//            System.out.println("Order ID: " + o.getId());
//            System.out.println("Customer ID: " + o.getCustomerId());
//            System.out.println("Customer Name: " + o.getCustomerName());
//            System.out.println("Order Date: " + o.getOrderDate());
//            System.out.println("Total Amount: " + o.getTotalAmount());
//            System.out.println("Order Status: " + o.getStatusString());
//        }
//    }


    //show order details by customer id
    private void showOrderDetailsByCustomerID() throws SQLException, IOException{
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(reader.readLine());
        ArrayList<OrderDetail> orders = orderManager.showOrderDetailsByCustomerID(id);
        for (OrderDetail o: orders){
            System.out.println("===================");
            System.out.println("Order Detail ID: " + o.getId());
            System.out.println("Customer Name: " + o.getCustomerName());
            System.out.println("Product Name: " + o.getProductName());
            System.out.println("Quantity: " + o.getQuantity());
            System.out.println("Unit Price: " + o.getUnitPrice());
            System.out.println("Discount: " + o.getDiscount());
            System.out.println("Total Price: " + o.getTotalPrice());
            System.out.println("===================");
        }
    }

    //get total money by customer id
    private void getTotalPriceByCustomerId() throws SQLException, IOException{
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(reader.readLine());
        double total = orderManager.getTotalPriceByCustomerId(id);
        System.out.println("Total price: " + total);
    }

//    Product
    private void getAllProducts() throws SQLException {
        ArrayList<Product> products = productController.getAllProducts();

        //lambda expression
        //nếu chỉ 1 biến có thể bỏ bớt ngoặc tròn
        products.forEach((product) ->{
            System.out.println("====================");
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("Product Description: " + product.getDescription());
        });
    }

    private void addProduct() throws SQLException, IOException {
        System.out.print("Enter Product Name: ");
        String name = reader.readLine();
        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(reader.readLine());
        System.out.print("Enter Product Description: ");
        String description = String.valueOf(reader.read());
        Product product = new Product(name, price, description);
        productController.addProduct(product);
    }

    private void removeProduct() throws SQLException, IOException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(reader.readLine());
        productController.removeProduct(id);
    }

    private void updateProduct() throws SQLException, IOException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Enter Product Name: ");
        String name = reader.readLine();
        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(reader.readLine());
        System.out.print("Enter Product Description: ");
        String description = String.valueOf(reader.read());
        Product product = new Product( name, price, description);
        productController.updateProduct(product, id);
    }

    private void getProductById() throws SQLException, IOException {
        System.out.print("Enter Product ID: ");
        int id = Integer.parseInt(reader.readLine());
        Product product = productController.getProductByID(id);
        System.out.println("====================");
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Product Price: " + product.getPrice());
        System.out.println("Product Description: " + product.getDescription());
    }

    private void getProductByName() throws SQLException, IOException {
        System.out.println("Enter Product Name: ");
        String name = reader.readLine();
        ArrayList<Product> products = productController.getProductsByName(name);
        if(products.isEmpty()){
            System.out.println("Product not found!");
        }else {
            for(Product product: products){
            System.out.println("====================");
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("Product Description: " + product.getDescription());
            }
        }
    }

    private int menu() throws IOException {
        System.out.println("=========MAIN MENU==============");
        System.out.println("1. Add, Update, Remove Customer");
        System.out.println("2. Add, Update, Remove Product");
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
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    removeCustomer();
                    break;
                case 4:
                    getAllCustomers();
                    break;
                case 5:
                    getCustomerById();
                    break;
                case 6:
                    getCustomerByName();
                    break;
                case 7:
                    start();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private int menuProduct() throws IOException {
        System.out.println("=======MENU PRODUCT==========");
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
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    getAllProducts();
                    break;
                case 5:
                    getProductById();
                    break;
                case 6:
                    getProductByName();
                    break;
                case 7:
                    start();
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
                    choice3();
                    break;
                case 4:
                    showOrderDetailsByCustomerID();
                    break;
                case 5:
                    getTotalPriceByCustomerId();
                    break;
                case 6:
                    updateOrderStatus();
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
                    System.exit(0);
                }
            }
        }
    }
}