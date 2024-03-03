package com.example;


import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TransactionTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional(readOnly = false)
    void test1() {
        userRepository.save(User.builder().name("huruiyi").password("password").email("1100@qq.com").build());
    }


    @Test
    @Transactional(readOnly = true)
    public void test2() throws InterruptedException {

        List<User> list1 = userRepository.findAll();
        System.out.println(list1.size());
        //睡眠5秒给insert事务提供时间
        System.out.println("=================睡眠20s==============");
        TimeUnit.SECONDS.sleep(20);
        //再次查询
        List<User> list2 = userRepository.findAll();
        System.out.println(list2.size());

    }

}
