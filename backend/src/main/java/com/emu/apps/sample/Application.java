package com.emu.apps.sample;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@EnableGlobalMethodSecurity(securedEnabled = false, prePostEnabled = false)
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class); 
        ApplicationContext ctx  =  app.run(args);
		//ApplicationContext ctx  = SpringApplication.run(Application.class, args);
		System.out.println("List of beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

	}

}
