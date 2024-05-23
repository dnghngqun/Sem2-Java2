import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeToJSonFileWriter {
//
//    public static void main(String[] args) throws IOException, SQLException {
//        BufferedWriter writer = Files.newBufferedWriter(Paths.get("EmpToJsonFile.json"));
//        List<Employee> employees = DatabaseConnectionDB.getEmployeeFromDatabase();
//        Map<Integer, Object> employee = new HashMap<>();
//
//        for (Employee emp : employees) {
//            employee.put(emp.getId(), emp.toString());
//        }
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();//in json dep hon
//        gson.toJson(employee, writer);
//        writer.close();
//
//    }

    public static void writeEmpToJsonFile(List<Employee> employees, String filepath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(filepath);
        gson.toJson(employees, writer);
        writer.close();
    }
}
