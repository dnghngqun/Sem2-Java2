package Mockapi;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class View {
    EmpController empController = new EmpController();

    public View() throws IOException {
    }

    public void start() throws IOException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    getEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }



    private int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Employee");
        System.out.println("2. Get Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

    private void getEmployee() throws IOException {
        System.out.println("Get Employee");
        List<Employee> dataList = empController.getEmployee();
        for(Employee emp : dataList){
            System.out.println("Id: " + emp.getId());
            System.out.println("Name: " + emp.getName());
            System.out.println("Address: " + emp.getAddress());
            System.out.println("Email: " + emp.getAge());
        }
    }

    public void addEmployee() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine());
        Employee emp = new Employee( name, address, age);
        System.out.println("POST response: " + empController.addEmployee(emp));
        sc.close();
    }

    private void updateEmployee() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine());
        Employee emp = new Employee(id, name, address, age);
        System.out.println("PUT response: " + empController.updateEmployee(emp));
        sc.close();
    }

    private void deleteEmployee() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("DELETE response id: "+ id );
        boolean result = empController.DeleteEmployee(id);
        if(result){
            System.out.println("Delete successfully");
        }else{
            System.out.println("Delete failed");
        }
        sc.close();
    }

}
