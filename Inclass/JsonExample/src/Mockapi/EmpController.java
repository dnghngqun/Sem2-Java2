package Mockapi;

import java.io.IOException;
import java.util.List;

public class EmpController {

    MockapiDemo mockapiDemo = new MockapiDemo();

    public EmpController() throws IOException {
    }

    public List<Employee> getEmployee() throws IOException {
        return mockapiDemo.getEmployee();
    }
    public String addEmployee(Employee emp) throws IOException {
       return mockapiDemo.addEmployee(emp);
    }

    public boolean DeleteEmployee(int id) throws IOException {
        return mockapiDemo.deleteEmployee(id);

    }

    public String updateEmployee(Employee emp) throws IOException {
        return mockapiDemo.updateEmployee(emp);
    }
}
