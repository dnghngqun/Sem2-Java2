import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Read_Json_simple {
    public static void main(String[] args) throws IOException, JsonException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("employee.json"));
        //tao ra bo parser(phan tich)
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
        BigDecimal id = (BigDecimal) parser.get("id");
        String name = (String) parser.get("name");
        BigDecimal age = (BigDecimal) parser.get("age");
      String email = (String) parser.get("email");
        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        System.out.println(email);

        //read address
        Map<Object, Object> address = (Map<Object, Object>) parser.get("address");

        address.forEach((key, value) -> System.out.println(key + " : " + value));

        //read prj
        JsonArray projects = (JsonArray) parser.get("projects");
        projects.forEach(entry -> {
            JsonObject project = (JsonObject) entry;
            String title = (String) project.get("title");
            BigDecimal budget = (BigDecimal) project.get("budget");
            System.out.println(title + " : " + budget);
        });

        reader.close();
    }

}
