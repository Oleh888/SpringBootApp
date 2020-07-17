package spring.boot.app.demo.serviceOperationsTest;

import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.service.ProductService;
import spring.boot.app.demo.service.UserService;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    private static final String FILE_TEST = "src/test/resources/test1.csv";
    private static ProductService productService;
    private static List<User> users;
    private static final String TEST_PASSWORD = "1111";

    @BeforeClass
    public static void setHelperObjects() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("spring.boot.app.demo");
        CustomFileReader reader = context.getBean(CustomFileReader.class);
        CustomCsvParser parser = context.getBean(CustomCsvParser.class);
        UserService userService = context.getBean(UserService.class);
        productService = context.getBean(ProductService.class);
        users = parser.getAllUsers(reader.getAll(FILE_TEST));
        users.forEach(user -> user.setPassword(TEST_PASSWORD));
        users.forEach(user -> user.setRole(User.Role.ADMIN));
        users.forEach(userService::create);
    }

    @Test
    public void getMostCommentedProductIsOk() {
        String expected = users.get(4).getProduct().getNativeId();
        List<String> actualUsers = productService.getMostCommentedLimitedTo(5);
        String actual = actualUsers.get(0);
        Assert.assertEquals(5, actualUsers.size());
        Assert.assertEquals(expected, actual);
    }
}
