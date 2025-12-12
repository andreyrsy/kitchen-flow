package dev.andreyrsy.orderly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OrderlyApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderlyApplication.class, args);
    }

}
