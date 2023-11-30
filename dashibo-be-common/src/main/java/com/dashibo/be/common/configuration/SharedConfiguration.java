package com.dashibo.be.common.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("com.dashibo.be")
@EnableMongoRepositories("com.dashibo.be")
@EntityScan("com.dashibo.be")
public class SharedConfiguration {
}
