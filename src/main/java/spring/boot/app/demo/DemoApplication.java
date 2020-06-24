package spring.boot.app.demo;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("spring.boot.app.demo.util");
        CustomFileReader fileReader = context.getBean(CustomFileReader.class);
        CustomCsvParser parser = context.getBean(CustomCsvParser.class);
        long time = System.currentTimeMillis();
        List<String> list = fileReader.getAll("E:/Reviews.csv");
        List<User> users = parser.getAllUsers(list);
        System.out.println("Time for reading and parsing data from file is "
                + (System.currentTimeMillis() - time) + " milliseconds");
        System.exit(-1);
    }

}
