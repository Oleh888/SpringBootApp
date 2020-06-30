package spring.boot.app.demo.utilTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import spring.boot.app.demo.model.Product;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.util.CustomCsvParser;
import spring.boot.app.demo.util.CustomFileReader;

public class CustomFileReaderAndParserTest {
    private static final String FILE_TEST = "src/test/resources/test1.csv";

    @Test
    public void readFromFileIsOk() {
        CustomFileReader fileReader = new CustomFileReader();
        List<String> actual = fileReader.getAll(FILE_TEST);
        Assert.assertEquals(11, actual.size());
    }

    @Test
    public void parseDataIsOk() {
        CustomFileReader fileReader = new CustomFileReader();
        List<String> actual = fileReader.getAll(FILE_TEST);
        CustomCsvParser csvParser = new CustomCsvParser();
        List<User> users = csvParser.getAllUsers(actual);
        User testUserOne = new User(new Product("B001E4KFG0"), "A3SGXH7AUHU8GW", "delmartian",
                1, 1, 5, convertToLocalDateTime("1303862400"),
                "Good Quality Dog Food", "I have bought several of the Vitality canned dog food "
                + "products and have found them all to be of good quality.");
        Assert.assertEquals(testUserOne, users.get(0));
        Assert.assertEquals("A1MZYO9TZK0BBI", users.get(8).getId());
        Assert.assertEquals(0, users.get(1).getHelpfulnessDenominator());
        Assert.assertEquals(1, users.get(2).getHelpfulnessNumerator());
        Assert.assertEquals(2, users.get(3).getScore());
        Assert.assertEquals(convertToLocalDateTime("1350777600"), users.get(4).getLocalDateTime());
        Assert.assertEquals("B006K2ZZ7K", users.get(5).getProduct().getId());
        Assert.assertEquals("Great!", users.get(6).getSummary());
        Assert.assertEquals("This taffy is so good.", users.get(7).getText());
    }

    private LocalDateTime convertToLocalDateTime(String milliseconds) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(milliseconds));
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
