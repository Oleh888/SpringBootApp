package spring.boot.app.demo.crudOperationsTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.service.UserService;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {
    private static final String FILE_TEST = "src/test/resources/test1.csv";
    private static UserService userService;
    private static List<User> users;

    @BeforeClass
    public static void setHelperObjects() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("spring.boot.app.demo");
        CustomFileReader reader = context.getBean(CustomFileReader.class);
        CustomCsvParser parser = context.getBean(CustomCsvParser.class);
        userService = context.getBean(UserService.class);
        List<String> actual = reader.getAll(FILE_TEST);
        users = parser.getAllUsers(actual);
        users.forEach(userService::save);
    }

    @Test
    public void creatingAndGettingUserIsOk() {
        List<User> usersFromDb = userService.findAll();
        Assert.assertEquals(users.size(), usersFromDb.size());
        for (int i = 0; i < users.size(); i++) {
            Assert.assertEquals(users.get(i), usersFromDb.get(i));
        }
    }

    @Test
    public void deletingUserIsOk() {
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
        for (User expectedUser : users) {
            Assert.assertEquals(expectedUser, userService.findById(expectedUser.getId())
                    .orElseThrow(RuntimeException::new));
        }
    }
}
