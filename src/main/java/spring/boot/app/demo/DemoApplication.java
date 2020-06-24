package spring.boot.app.demo;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.boot.app.demo.util.CustomFileReader;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("spring.boot.app.demo.util");
        CustomFileReader fileReader = context.getBean(CustomFileReader.class);
        long timeStart = System.currentTimeMillis();
        List<String> list = fileReader.getAll("E:/Reviews.csv");
        long countLine = 0;
        for (String s : list) {
            countLine++;
        }
        System.out.println("Time for reading " + (System.currentTimeMillis() - timeStart) + " milliseconds");
        System.out.println("Amount of lines " + countLine);
        System.exit(-1);
    }

}
