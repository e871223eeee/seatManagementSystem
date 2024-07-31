package com.hu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.hu.mapper")
public class SeatManagementApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SeatManagementApplication.class,args);
    }
}
