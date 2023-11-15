package com.demo.smartport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCaching
@MapperScan("com.demo.smartport.Mapper")
public class SmartportApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartportApplication.class, args);
    }

}
