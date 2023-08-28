package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2ServerApplication {

    /**
     * http://localhost:9401/oauth/authorize?response_type=code&client_id=admin&redirect_uri=http://www.baidu.com&scope=all&state=normal
     *
     * 对外提供：
     * http://localhost:9401/user/getCurrentUser?access_token=zcfIExOfJVW032EzjIhANjtHnCs
     *
     * 内部提供：
     * http://localhost:9401/admin/welcome
     * http://localhost:9401/welcome
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
