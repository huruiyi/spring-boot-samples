package com.example.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Jackson_Test {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> userData = mapper.readValue(new File("user.json"), Map.class);
            System.out.println(userData);
            userData = new HashMap<String, Object>();
            Map<String, String> nameStruct = new HashMap<String, String>();
            nameStruct.put("first", "ba");
            nameStruct.put("last", "ka");
            userData.put("name", nameStruct);
            userData.put("gender", "MALE");
            userData.put("verified", Boolean.FALSE);
            userData.put("userImage", "Rm9vYmFyIQ==");
            mapper.writeValue(new File("user-modified.json"), userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
