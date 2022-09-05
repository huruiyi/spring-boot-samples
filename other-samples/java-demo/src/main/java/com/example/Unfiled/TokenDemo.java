package com.example.Unfiled;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TokenDemo {

    public class Persion {
        public Long id;
        public String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static final String SECRET = "SlashSecret";         // JWT密码


    @Test
    public void test() {
        Persion user = new Persion();
        user.setId(124l);
        user.setName("admin");
        String token = Jwts.builder()
                .setSubject(JSON.toJSONString(user))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 * 365*100))
                .signWith(SignatureAlgorithm.HS512, SECRET) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();

        System.out.println(token);
    }
}
