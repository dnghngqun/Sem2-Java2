import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ApplicationJson {
    public static void main(String[] args) throws SQLException, IOException {
        List<Employee> employees = DatabaseConnectionDB.getEmployeeFromDatabase();

        String filepath = "EmpToJsonFile.json";
        EmployeeToJSonFileWriter.writeEmpToJsonFile(employees, filepath);

    }
}
