import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Read_JSON_Jackson {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("employee.json"));

        ObjectMapper mapper = new ObjectMapper();

        //Read Employee -> doc tu dong reader
        JsonNode parser = mapper.readTree(reader);

        //list details employee
        System.out.println(parser.path("id").asLong());
        System.out.println(parser.path("name").asText());
        System.out.println(parser.path("age").asLong());
        System.out.println(parser.path("email").asText());


        //list address
        JsonNode address = parser.path("address");
        System.out.println(address.path("zipCode").asLong());
        System.out.println(address.path("city").asText());
        System.out.println(address.path("street").asText());

        //list project
        JsonNode project = parser.path("projects");
        project.forEach(entry ->{
            JsonNode prj = (JsonNode) entry;
            System.out.println(prj.path("title").asText());
            System.out.println(prj.path("budget").asLong());
        });

        reader.close();
    }
}
