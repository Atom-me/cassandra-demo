package com.atom.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CassandraDemoApplication {
    private final static Logger log = LoggerFactory.getLogger(CassandraDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CassandraDemoApplication.class, args);
    }

}
