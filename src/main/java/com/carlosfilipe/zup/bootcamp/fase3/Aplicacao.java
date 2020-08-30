package com.carlosfilipe.zup.bootcamp.fase3;

import java.io.IOException;

import com.carlosfilipe.zup.bootcamp.fase3.controller.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class Aplicacao {
    
    public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        
    }

}