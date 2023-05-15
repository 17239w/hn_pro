package com.hn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hn.mapper")
public class HnProApplication {

    public static void main(String[] args) {
        SpringApplication.run(HnProApplication.class, args);
    }

}
