import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectionDB {
    private static final String String_JDBC_URL = "jdbc:mysql://localhost:3306/FPT_SESSION10";
    private static final String user = "root";
    private static final String password = "";

    public static List<Employee> getEmployeeFromDatabase() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = DriverManager.getConnection(String_JDBC_URL, user, password);
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM EMPLOYEE");
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setAddress(rs.getString("address"));
            employee.setAge(rs.getInt("age"));
            employees.add(employee);
        }
        rs.close();
        stm.close();
        connection.close();
        return employees;
    }

}
