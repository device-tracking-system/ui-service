package pl.edu.agh.iet.dts.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Bartłomiej Grochal
 */
@SpringBootApplication
@EnableEurekaClient
public class UIService {

    public static void main(String[] args) {
        SpringApplication.run(UIService.class, args);
    }

}
