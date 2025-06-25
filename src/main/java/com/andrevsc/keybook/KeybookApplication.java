package com.andrevsc.keybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // adicione este import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // adicione este import

@SpringBootApplication
@EntityScan(basePackages = "com.andrevsc.keybook.model") // adicione esta linha
@EnableJpaRepositories(basePackages = "com.andrevsc.keybook.repository") // adicione esta linha
public class KeybookApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeybookApplication.class, args);
    }

}
