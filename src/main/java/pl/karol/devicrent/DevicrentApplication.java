package pl.karol.devicrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.karol.devicrent.app.DevicrentController;

import java.util.Scanner;

@SpringBootApplication
public class DevicrentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DevicrentApplication.class, args);
        DevicrentController devicrentController = ctx.getBean(DevicrentController.class);
        devicrentController.mainLoop();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
