package com.nairs.referenceapp.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CassandraConfig {
     @Bean
     public Session session() {
        Cluster cluster = Cluster.builder()
                .addContactPoints("localhost")
                .withoutJMXReporting()
                .build();
        return cluster.connect("pricedb");
    }

}
