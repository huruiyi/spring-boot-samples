package com.example.xml.ch.ch12.JSONPDemo.v2;

import javax.json.*;
import java.io.*;

import static java.lang.System.out;

public class JSONPDemo {

    public static void main(String[] args)
            throws FileNotFoundException {
        String json =
                "{" +
                        "   \"firstName\": \"John\"," +
                        "   \"lastName\": \"Doe\"," +
                        "   \"age\": 42," +
                        "   \"address\":" +
                        "   {" +
                        "      \"street\": \"400 Some Street\"," +
                        "      \"city\": \"Beverly Hills\"," +
                        "      \"state\": \"CA\"," +
                        "      \"zipcode\": 90210" +
                        "   }," +
                        "   \"phoneNumbers\":" +
                        "   [" +
                        "      {" +
                        "         \"type\": \"home\"," +
                        "         \"number\": \"310 555-1234\"" +
                        "      }," +
                        "      {" +
                        "         \"type\": \"fax\"," +
                        "         \"number\": \"310 555-4567\"" +
                        "      }" +
                        "   ]" +
                        "}";
        File file = new File("person.json");
        JsonReader reader =
                Json.createReader(file.exists() ?
                        new FileReader("person.json") :
                        new StringReader(json));
        JsonObject person = reader.readObject();
        out.printf("First name: %s%n",
                person.getString("firstName"));
        out.printf("Last name: %s%n",
                person.getString("lastName"));
        out.printf("Age: %d%n", person.getInt("age"));
        out.println("Address");
        out.println("-------");
        JsonObject address = person.getJsonObject("address");
        out.printf("   Street: %s%n",
                address.getString("street"));
        out.printf("   City: %s%n",
                address.getString("city"));
        out.printf("   State: %s%n",
                address.getString("state"));
        out.printf("   Zipcode: %d%n",
                address.getInt("zipcode"));
        out.println("Phone Numbers");
        out.println("-------------");
        JsonArray phoneNumbers =
                person.getJsonArray("phoneNumbers");
        for (JsonObject phoneNumber :
                phoneNumbers.getValuesAs(JsonObject.class)) {
            out.printf("   Type: %s%n",
                    phoneNumber.getString("type"));
            out.printf("   Number: %s%n",
                    phoneNumber.getString("number"));
        }
        try (FileWriter fw = new FileWriter("person.json")) {
            JsonWriter writer = Json.createWriter(fw);
            writer.writeObject(person);
            writer.close();
        } catch (IOException ioe) {
            out.printf("I/O error: %s%n", ioe.getMessage());
        }
    }
}
