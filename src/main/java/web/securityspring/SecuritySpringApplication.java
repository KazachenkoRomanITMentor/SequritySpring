package web.securityspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecuritySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritySpringApplication.class, args);
    }

}
