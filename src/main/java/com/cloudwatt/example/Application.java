package com.cloudwatt.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@Configuration
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
// Sprint Boot Auto Configuration
@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	  public static void main(String... args) {
      SpringApplication app = new SpringApplication(Application.class);
      // app.setBannerMode(Mode.OFF);
      app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(Application.class);
    }
    

}
