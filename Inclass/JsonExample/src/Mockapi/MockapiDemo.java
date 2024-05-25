package Mockapi;


import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MockapiDemo {
  private static final String url_api = "https://665041f0ec9b4a4a6031520b.mockapi.io/api/employee";

  URL url = new URL(url_api);
  HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    public MockapiDemo() throws IOException {
    }

    public List<Employee> getEmployee() throws IOException {
      List<Employee> datalist = new ArrayList<>();

      connection.setRequestMethod("GET");

      int responseCode = connection.getResponseCode();
      if(responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(
                //chuyển đổi luồng vào từ byte -> ký tự
                new java.io.InputStreamReader(
                        //trả về giá trị đọc được từ api
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;//
        while ((line = reader.readLine()) != null) {
          response.append(line);
        }
        Gson gson = new Gson();
        JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
        for (JsonElement element : jsonArray) {
          Employee emp = gson.fromJson(element, Employee.class);
          datalist.add(emp);
          System.out.println("Success!");
        }
      }else {
          System.out.println("GET request failed with response code: " + responseCode);
        }

      return datalist;
  }

  public String addEmployee(Employee emp) throws IOException {
    connection.setRequestMethod("POST");
    //Giá trị "application/json" cho biết rằng nội dung của yêu cầu HTTP được gửi dưới dạng dữ liệu JSON.
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true); //true để cho phép ghi dữ liệu vào yêu cầu.
    Gson gson = new Gson();
    String empJson = gson.toJson(emp);

    OutputStream os = connection.getOutputStream();
    byte[] input = empJson.getBytes("utf-8");
    os.write(input, 0, input.length);

    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
    StringBuilder response = new StringBuilder();
    String responseLine = null;
    while ((responseLine = br.readLine()) != null) {
      response.append(responseLine.trim());
    }
    return response.toString();
  }

  public boolean deleteEmployee(int id) throws IOException {
    URL url1 = new URL(url_api + "/" + id);
    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
      connection.setRequestMethod("DELETE");

    int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
      System.out.println("DELETE request successful");
      return true;
    } else {
        return false;

    }
  }

  public String updateEmployee(Employee emp) throws IOException {
      URL url1 = new URL(url_api + "/" + emp.getId());
      HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
      connection.setRequestMethod("PUT");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);

      Gson gson = new Gson();
      String empJson = gson.toJson(emp);

      OutputStream os = connection.getOutputStream();
      byte[] input = empJson.getBytes("utf-8");
      os.write(input, 0, input.length);

      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
      StringBuilder response = new StringBuilder();
      String responseLine = null;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      return response.toString();
    }

}
