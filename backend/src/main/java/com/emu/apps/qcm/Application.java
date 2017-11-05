package com.emu.apps.qcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
// @EnableGlobalMethodSecurity(securedEnabled = false, prePostEnabled = false)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
