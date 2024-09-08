package com.example.model.serializers;

import com.example.model.Order;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

@JsonComponent
public class SimpleSerializers {

  public static class OrderSerializer extends JsonObjectSerializer<Order> {

    @Override
    protected void serializeObject(Order value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeStringField("1:LastName", value.getCustomer().getLastName());
      jgen.writeStringField("2:FirstName", value.getCustomer().getFirstName());
      jgen.writeNumberField("3:amount", value.getQuantity());
      jgen.writeStringField("4:Product", value.getProductName());
    }
  }

}
