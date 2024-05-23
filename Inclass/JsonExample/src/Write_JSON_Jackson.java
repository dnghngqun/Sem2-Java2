
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Write_JSON_Jackson {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("employee.json"));

        //tao doi tượng employee với Map
        Map<String, Object> employee = new HashMap<>();
        employee.put("id", 1);
        employee.put("name", "John");
        employee.put("age", 25);
        employee.put("email", "example@gmail.com");

        //tao doi tượng address với Map
        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 Main Street");
        address.put("city", "Hnoi");
        address.put("zipCode", 100000);

        //thêm địa chỉ cho nhân viên
        employee.put("address", address);

        //tạo đối tượng project
        Map<String, Object> prj1 = new HashMap<>();
        prj1.put("title", "Json Application");
        prj1.put("budget", 15000);

        Map<String, Object> prj2 = new HashMap<>();
        prj2.put("title", "Employee Management");
        prj2.put("budget", 12000);

        // thêm prj vào employee
        employee.put("projects", Arrays.asList(prj1, prj2));

        //tạo object: ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // ghi đối tượng ra  Json file
        writer.write(mapper.writeValueAsString(employee));
        writer.close();
    }
}
