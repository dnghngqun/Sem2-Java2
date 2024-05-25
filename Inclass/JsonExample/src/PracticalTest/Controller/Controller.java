package PracticalTest.Controller;

import PracticalTest.Entity.Student;
import PracticalTest.Model.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    Model model = new Model();

    public void writeStudentToJsonFile(String filepath) throws IOException, SQLException {
        model.writeStudentToJsonFile(filepath);
    }

    public List<Student> readJSonFile(String filepath) throws IOException, SQLException {
        return model.readJSonFile(filepath);
    }

    public Student searchStudentById(int id, String filepath) throws IOException, SQLException {
        return model.searchStudentById(id, filepath);
    }

    public Student searchStudentByName(String name, String filepath) throws SQLException, IOException {
        return model.searchStudentByName(name, filepath);
    }
}
