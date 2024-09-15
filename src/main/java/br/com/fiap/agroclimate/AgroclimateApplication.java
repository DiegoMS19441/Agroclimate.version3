package br.com.fiap.agroclimate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgroclimateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgroclimateApplication.class, args);
    }

}
