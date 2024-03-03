package com.example;

import com.example.enums.SexEnum;
import com.example.mapper.UserMapper;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Value("${local.server.port}")
    private String port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    void getHello() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertEquals(response.getBody(), "Greetings from Spring Boot!");
    }

    @Test
    void testInsert() {
        userMapper.insertUser(User.builder().userName("huruiyi").sex(SexEnum.MALE).email("1100@qq.com").mobile("13612349876").build());
    }

    @Test
    @Transactional(readOnly = true) // mybatis缓存-会查询一次
    void testSelect() throws InterruptedException {
        List<User> userList1 = userMapper.selectAllUsers();
        userList1.forEach(user -> {
            System.out.println(user);
        });
        TimeUnit.SECONDS.sleep(20);
        List<User> userList2 = userMapper.selectAllUsers();
        userList2.forEach(user -> {
            System.out.println(user);
        });
    }

}

