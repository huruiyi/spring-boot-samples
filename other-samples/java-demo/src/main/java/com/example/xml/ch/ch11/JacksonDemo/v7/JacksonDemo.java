package com.example.xml.ch.ch11.JacksonDemo.v7;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import static java.lang.System.out;

public class JacksonDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Person person =
                mapper.readValue(new File("person.json"),
                        Person.class);
        out.println(person);
    }
}
