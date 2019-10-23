package com.example.pty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.pty.mapper")
public class PtyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtyApplication.class, args);
    }

}
