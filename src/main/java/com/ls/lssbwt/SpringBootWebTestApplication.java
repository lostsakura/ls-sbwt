package com.ls.lssbwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.ls.lssbwt.repository")
public class SpringBootWebTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebTestApplication.class, args);
    }

}
