package PracticalTest.View;

import Mockapi.Employee;
import PracticalTest.Controller.Controller;
import PracticalTest.Entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class View {
    private String filepath = null;
    Controller controller = new Controller();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String dateFormatPattern = "MMM dd, yyyy";
    SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
    private void writeStudentToJsonFile() throws IOException, SQLException {
        System.out.println("Enter the json file path to write");
        System.out.println("Example: student.json");
        System.out.println("Recent file path: " + filepath);
        System.out.print("Enter file path: ");
        filepath = reader.readLine();
        controller.writeStudentToJsonFile(filepath);
        System.out.println("Write successfully");
    }

    public void start() throws IOException, SQLException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1:
                    writeStudentToJsonFile();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private void searchStudent() throws IOException, SQLException {
        System.out.println("Enter the json file path to read");
        System.out.println("Recent file path: " + filepath);
        System.out.print("Enter file path: ");
        filepath = reader.readLine();
        controller.readJSonFile(filepath);
        System.out.println("Read Information Student successfully");
        while (true) {
            int choice = menuSearchStd();
            switch (choice){
                case 1:
                    searchStudentById(filepath);
                    break;
                case 2:
                    searchStudentByName(filepath);
                    break;
                case 3:
                    start();
                    break;
            }
        }
    }
    private void searchStudentById(String filepath) throws IOException, SQLException {
        System.out.print("Enter id student to search: ");
        int id = Integer.parseInt(reader.readLine());
        Student std = controller.searchStudentById(id, filepath);
        System.out.println("id: " + std.getId() +
                "\nname: " + std.getName()  +
                "\naddress: " + std.getAddress()  +
                "\nemail: " + std.getEmail()  +
                "\nphone: " + std.getPhone()  +
                "\nDOB: " + dateFormat.format(std.getDOB()));
    }

    private void searchStudentByName(String filepath) throws IOException, SQLException {
        System.out.print("Enter name student to search: ");
        String name = reader.readLine();
        Student std = controller.searchStudentByName(name, filepath);
        if(std == null){
            System.out.println("Not found information");
            return;
        }
        System.out.println("id: " + std.getId() +
                "\nname: " + std.getName()  +
                "\naddress: " + std.getAddress()  +
                "\nemail: " + std.getEmail()  +
                "\nphone: " + std.getPhone()  +
                "\nDOB: " + dateFormat.format(std.getDOB()));
    }
    private int menu() throws IOException {
        System.out.println("1. Write student to json file");
        System.out.println("2. Search student information");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
        return Integer.parseInt(reader.readLine());
    }

    private int menuSearchStd() throws IOException {
        System.out.println("1. Search by id");
        System.out.println("2. Search by name");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
        return Integer.parseInt(reader.readLine());
    }
}
