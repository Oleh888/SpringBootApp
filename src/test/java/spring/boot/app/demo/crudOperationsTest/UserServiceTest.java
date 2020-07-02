package spring.boot.app.demo.crudOperationsTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.service.UserService;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;

import java.util.List;

public class UserServiceTest {
    private static final String FILE_TEST = "src/test/resources/test1.csv";
    private AnnotationConfigApplicationContext context;
    private CustomFileReader reader;
    private CustomCsvParser parser;
    private UserService userService;

    @Before
    public void setHelperObjects() {
        context = new AnnotationConfigApplicationContext("spring.boot.app.demo");
        reader = new CustomFileReader();
        parser = new CustomCsvParser();
        userService = context.getBean(UserService.class);
    }

    @Test
    public void creatingAndGettingUserIsOk() {
        List<String> actual = reader.getAll(FILE_TEST);
        List<User> users = parser.getAllUsers(actual);
        users.forEach(userService::save);
        List<User> usersFromDb = userService.findAll();
        Assert.assertEquals(users.size(), usersFromDb.size());
        for (int i = 0; i < users.size(); i++) {
            Assert.assertEquals(users.get(i), usersFromDb.get(i));
        }
    }

    @Test
    public void deletingUserIsOk() {
        List<String> actual = reader.getAll(FILE_TEST);
        List<User> users = parser.getAllUsers(actual);
        users.forEach(userService::save);
        userService.delete(users.get(0));
        users.remove(users.get(0));
        List<User> usersFromDb = userService.findAll();
        Assert.assertEquals(users.size(), usersFromDb.size());
        for (int i = 0; i < users.size(); i++) {
            Assert.assertEquals(users.get(i), usersFromDb.get(i));
        }
    }

    @Test
    public void getUserByIdIsOk() {
        List<String> actual = reader.getAll(FILE_TEST);
        List<User> users = parser.getAllUsers(actual);
        users.forEach(userService::save);
        for (User expectedUser : users) {
            Assert.assertEquals(expectedUser, userService.findById(expectedUser.getId())
                    .orElseThrow(RuntimeException::new));
        }
    }
}
