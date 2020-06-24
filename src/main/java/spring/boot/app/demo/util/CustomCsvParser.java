package spring.boot.app.demo.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import spring.boot.app.demo.model.Product;
import spring.boot.app.demo.model.User;

@Component
public class CustomCsvParser {
    private static final Logger LOGGER = Logger.getLogger(CustomCsvParser.class);
    private static final int MAX_CHARS_PER_COLUMN = 100000;

    public List<User> getAllUsers (List<String> lines) {
        List<User> users = new LinkedList<>();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setMaxCharsPerColumn(MAX_CHARS_PER_COLUMN);
        CsvParser csvParser = new CsvParser(settings);
        lines.remove(0);
        for (String line : lines) {
            String[] data = csvParser.parseLine(line);
            users.add(getUserFromLine(data));
        }
        LOGGER.info("Information from file was successfully parsed.");
        return users;
    }

    private LocalDateTime convertToLocalDateTime(String milliseconds) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(milliseconds));
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private User getUserFromLine(String[] data) {
        User user = new User();
        Product product = new Product();
        product.setId(data[1]);
        user.setProduct(product);
        user.setId(data[2]);
        user.setProfileName(data[3]);
        user.setHelpfulnessNumerator(Integer.parseInt(data[4]));
        user.setHelpfulnessDenominator(Integer.parseInt(data[5]));
        user.setScore(Integer.parseInt(data[6]));
        user.setLocalDateTime(convertToLocalDateTime(data[7]));
        user.setSummary(data[8]);
        user.setText(data[9]);
        return user;
    }
}
