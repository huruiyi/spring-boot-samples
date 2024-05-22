package com.example;


import java.nio.charset.StandardCharsets;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootTest
public class HelloControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void hello() throws Exception {
    String contentType = (new MediaType("application", "json", StandardCharsets.UTF_8)).toString();
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/demo/sayHello")
            .contentType(contentType)
            .param("name", "World")
            .accept(contentType))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("hello World"))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Assertions.assertEquals("hello World", content);
  }


  @Test
  public void scriptEngineTest() {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
    try {
      jsEngine.eval("print('Hello JavaScript in Java')");
    } catch (ScriptException ex) {
      log.error("JavaScript expression cannot be evaluated!", ex);
    }
  }

  @Test
  public void scriptEngineInfoTest() {
    ScriptEngineManager mgr = new ScriptEngineManager();
    mgr.getEngineFactories().forEach(factory -> {
      String engineName = factory.getEngineName();
      String languageName = factory.getLanguageName();
      String version = factory.getLanguageVersion();
      log.info("Engine name: {}，Language: {}，Version: {}", engineName, languageName, version);
    });
  }

}
