package com.example.web;

import com.example.model.Course;
import com.example.model.Customer;
import com.example.model.Order;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DataController {

  @RequestMapping("/courses")
  public List<Course> retrieveAllCourses() {
    return Arrays.asList(new Course(1, "Learn AWS", "in28 minutes"), new Course(2, "Learn DevOps", "in2 8minutes"),
        new Course(3, "Learn Azure", "in28 minutes"), new Course(4, "Learn GCP", "in28 minutes"));
  }

  @RequestMapping("/orders")
  public Order getOrders() {
    return Order.builder().id(1).customer(Customer.builder().id(1).lastName("ruiyi").firstName("hu").build()).orderDate(new Date())
        .productName("Acme Portal").quantity(100).build();
  }

  @ResponseBody
  @RequestMapping(value = "/user/{userId}/roles/{roleId}", method = RequestMethod.GET)
  public String getLogin(@PathVariable("userId") String userId, @PathVariable("roleId") String roleId) {
    return "User Id : " + userId + " Role Id : " + roleId;
  }

  @ResponseBody
  @RequestMapping(value = "/javabeat/{regexp1:[a-z-]+}", method = RequestMethod.GET)
  public String getRegExp(@PathVariable("regexp1") String regexp1) {
    return "URI Part : " + regexp1;
  }
}
