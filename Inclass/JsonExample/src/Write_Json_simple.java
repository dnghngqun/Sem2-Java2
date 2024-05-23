import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Write_Json_simple {
    public static void main(String[] args) throws IOException {
//        tạo ra bộ writer
        //tạo ra dòng ghi (writer)
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("employee.json"));
        //tạo đối tượng employee
        JsonObject employee = new JsonObject();
        employee.put("id", 1);
        employee.put("name", "John");
        employee.put("age", 25);
        employee.put("email", "example@gmail.com");
       //tạo đối tượng address
        JsonObject address = new JsonObject();
        address.put("street", "123 Main Street");
        address.put("city", "Hnoi");
        address.put("state", "CA");


        //thêm địa chỉ cho nhân viên
        employee.put("address", address);

        //tao prj 1
        JsonArray projects = new JsonArray();
        JsonObject p1 = new JsonObject();
        p1.put("title", "Emp management");
        p1.put("budget", 15000);

        JsonObject p2 = new JsonObject();
        p2.put("title", "Push sale");
        p2.put("budget", 12000);

        JsonObject p3 = new JsonObject();
        p3.put("title", "Push sale");
        p3.put("budget", 12000);

        //them 3 obj vaof jsonArray
        projects.addAll(Arrays.asList(p1,p2, p3));

        //add prj to employee
        employee.put("projects", projects);

        //ghi file json
        Jsoner.serialize(employee, writer);
        //ghi xong thi dong code
        writer.close();


    }
}
