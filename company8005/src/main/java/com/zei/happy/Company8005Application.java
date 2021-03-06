package com.zei.happy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zei.happy.mapper")
public class Company8005Application {

	public static void main(String[] args) {
		SpringApplication.run(Company8005Application.class, args);
	}

}
