import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Read_JSON_gson {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("employee.json"));

        //tao doi tuong JsonObject(Gson)
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

        //details
        System.out.println(parser.get("id").getAsLong());
        System.out.println(parser.get("name").getAsString());
        System.out.println(parser.get("age").getAsInt());
        System.out.println(parser.get("email").getAsString());

        //address
        JsonObject address = parser.get("address").getAsJsonObject();
        System.out.println(address.get("street").getAsString());
        System.out.println(address.get("city").getAsString());
        System.out.println(address.get("zipCode").getAsInt());

        //projects
        for(JsonElement prj : parser.get("projects").getAsJsonArray()){
            JsonObject project = prj.getAsJsonObject();
            System.out.println(project.get("title").getAsString());
            System.out.println(project.get("budget").getAsInt());
        }

        //or

//        JsonArray prj = parser.get("projects").getAsJsonArray();
//        prj.forEach(entry -> {
//            JsonObject project = entry.getAsJsonObject();
//            System.out.println(project.get("title").getAsString());
//            System.out.println(project.get("budget").getAsInt());
//        });

        reader.close();


    }
}
