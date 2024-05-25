package PracticalTest.Model;

import PracticalTest.Entity.Student;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final String dbName = "PracticalTestJSON";
    private static final String String_JDBC_URL = "jdbc:mysql://localhost:3306/" + dbName;
    private static final String user = "root";
    private static final String password = "";

    private List<Student> getStudentFromDatabase() throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection conn = DriverManager.getConnection(String_JDBC_URL, user, password);
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM Student");
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setEmail(rs.getString("email"));
            student.setPhone(rs.getString("phone"));
            student.setDOB(rs.getDate("dob"));
            student.setAddress(rs.getString("address"));
            students.add(student);
        }
        rs.close();
        stm.close();
        conn.close();
        return students;
    }

    public void writeStudentToJsonFile(String filepath) throws IOException, IOException, SQLException {
        List<Student> students = getStudentFromDatabase();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(filepath);
        gson.toJson(students, writer);
        writer.close();
    }

    public List<Student> readJSonFile(String filepath) throws IOException {
        String dateFormat = "MMM dd, yyyy";
        BufferedReader reader = Files.newBufferedReader(Paths.get(filepath));
        JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
        List<Student> data = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(dateFormat).create();
        for (JsonElement jsonElement : jsonArray) {
            Student std = gson.fromJson(jsonElement, Student.class);
            data.add(std);
        }
        return data;
    }

    public Student searchStudentById(int id, String filepath) throws SQLException, IOException {
        List<Student> students = readJSonFile (filepath);
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    public Student searchStudentByName(String name, String filepath) throws SQLException, IOException {
        List<Student> students = readJSonFile (filepath);
        for (Student student : students) {
            if(student.getName().equals(name)){
                return student;
            }
        }
        return null;
    }

}
