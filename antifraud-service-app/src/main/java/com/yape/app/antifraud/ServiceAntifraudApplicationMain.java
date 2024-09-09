package com.yape.app.antifraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class ServiceAntifraudApplicationMain {
    public static void main(String[] args) {
        Environment env = SpringApplication.run(ServiceAntifraudApplicationMain.class, args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t"
                        .concat("Application '{}' is running! Access URLs:\n\t")
                        .concat("Local: \t\thttp://localhost:{}\n\t")
                        .concat("Profile(s): \t{}\n----------------------------------------------------------"),
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}