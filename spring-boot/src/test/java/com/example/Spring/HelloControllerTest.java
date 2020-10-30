package com.example.Spring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //使用上下文构建mockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void hello() throws Exception {
        // 得到MvcResult自定义验证
        // 执行请求
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hello/sayHi")
                //.post("/hello") 发送post请求
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //传入参数
                .param("name", "Pangni")
                // .accept(MediaType.TEXT_HTML_VALUE))
                //接收的类型
                .accept(MediaType.APPLICATION_JSON_UTF8))
                //等同于Assert.assertEquals(200,status);
                //判断接收到的状态是否是200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //等同于 Assert.assertEquals("hello longzhonghua",content);
                .andExpect(MockMvcResultMatchers.content().string("hello Pangni"))
                .andDo(MockMvcResultHandlers.print())
                //返回MvcResult
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("hello Pangni", content);
    }
}
