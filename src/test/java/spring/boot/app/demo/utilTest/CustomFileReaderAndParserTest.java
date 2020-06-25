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
    private static final String FILE_TEST1 = "src/main/resources/test1.csv";

    @Test
    public void readFromFileIsOk() {
        CustomFileReader fileReader = new CustomFileReader();
        List<String> actual = fileReader.getAll(FILE_TEST1);
        Assert.assertEquals(11, actual.size());
    }

    @Test
    public void parseDataIsOk() {
        CustomFileReader fileReader = new CustomFileReader();
        List<String> actual = fileReader.getAll(FILE_TEST1);
        CustomCsvParser csvParser = new CustomCsvParser();
        List<User> users = csvParser.getAllUsers(actual);
        User testUserOne = new User(new Product("B001E4KFG0"), "A3SGXH7AUHU8GW", "delmartian",
                1, 1, 5, convertToLocalDateTime("1303862400"),
                "Good Quality Dog Food", "I have bought several of the Vitality canned dog food "
                + "products and have found them all to be of good quality.");
        Assert.assertEquals(testUserOne, users.get(0));
        Assert.assertEquals(users.get(8).getId(), "A1MZYO9TZK0BBI");
        Assert.assertEquals(users.get(1).getHelpfulnessDenominator(), 0);
        Assert.assertEquals(users.get(2).getHelpfulnessNumerator(), 1);
        Assert.assertEquals(users.get(3).getScore(), 2);
        Assert.assertEquals(users.get(4).getLocalDateTime(), convertToLocalDateTime("1350777600"));
        Assert.assertEquals(users.get(5).getProduct().getId(), "B006K2ZZ7K");
        Assert.assertEquals(users.get(6).getSummary(), "Great!");
        Assert.assertEquals(users.get(7).getText(), "This taffy is so good.");
    }

    private LocalDateTime convertToLocalDateTime(String milliseconds) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(milliseconds));
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
