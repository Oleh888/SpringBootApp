package spring.boot.app.demo.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.service.UserService;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;

@RestController
@RequestMapping(value = "/data")
public class InjectData {
    private static final Logger LOGGER = Logger.getLogger(InjectData.class);
    private final CustomFileReader reader;
    private final CustomCsvParser parser;
    private final UserService userService;

    public InjectData(CustomFileReader reader, CustomCsvParser parser, UserService userService) {
        this.reader = reader;
        this.parser = parser;
        this.userService = userService;
    }

    @GetMapping(value = "/inject")
    public String injectDataToDb(@RequestParam String directory) {
        List<User> users = parser.getAllUsers(reader.getAll(directory));
        LOGGER.info("Start injecting data to the DB");
        userService.createAll(users);
        LOGGER.info("Finish injecting data to the DB");
        return "Data was successfully injected to the DB";
    }
}
