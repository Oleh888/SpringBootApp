package spring.boot.app.demo.serviceOperationsTest;

import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.service.ProductService;
import spring.boot.app.demo.service.UserService;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductServiceTest {
    private static final String FILE_TEST = "src/test/resources/test1.csv";
    private static UserService userService;
    private static ProductService productService;
    private static List<User> users;

    @BeforeClass
    public static void setHelperObjects() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("spring.boot.app.demo");
        CustomFileReader reader = context.getBean(CustomFileReader.class);
        CustomCsvParser parser = context.getBean(CustomCsvParser.class);
        userService = context.getBean(UserService.class);
        productService = context.getBean(ProductService.class);
        List<String> actual = reader.getAll(FILE_TEST);
        users = parser.getAllUsers(actual);
        users.forEach(userService::create);
    }

    @Test
    public void getMostCommentedProductIsOk() {
        String expected = users.get(4).getProduct().getId();
        String actual = productService.getMostCommentedLimitedTo(10).get(0);
        Assert.assertEquals(expected, actual);
    }
}
