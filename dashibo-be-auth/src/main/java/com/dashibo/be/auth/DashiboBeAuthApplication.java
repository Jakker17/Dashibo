package com.dashibo.be.auth;

import com.dashibo.be.common.configuration.SecurityConfiguration;
import com.dashibo.be.common.configuration.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@Import({SharedConfiguration.class, SecurityConfiguration.class})
public class DashiboBeAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashiboBeAuthApplication.class, args);
    }

}
