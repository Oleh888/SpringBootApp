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
    private static final int PRODUCT_ID_INDEX = 1;
    private static final int USER_ID_INDEX = 2;
    private static final int PROFILE_NAME_INDEX = 3;
    private static final int HELPFULNESS_NUMERATOR_INDEX = 4;
    private static final int HELPFULNESS_DENOMINATOR_INDEX = 5;
    private static final int SCORE_INDEX = 6;
    private static final int LOCALE_DATE_TIME_INDEX = 7;
    private static final int SUMMARY_INDEX = 8;
    private static final int TEXT_INDEX = 9;

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
        product.setId(data[PRODUCT_ID_INDEX]);
        user.setProduct(product);
        user.setId(data[USER_ID_INDEX]);
        user.setProfileName(data[PROFILE_NAME_INDEX]);
        user.setHelpfulnessNumerator(Integer.parseInt(data[HELPFULNESS_NUMERATOR_INDEX]));
        user.setHelpfulnessDenominator(Integer.parseInt(data[HELPFULNESS_DENOMINATOR_INDEX]));
        user.setScore(Integer.parseInt(data[SCORE_INDEX]));
        user.setLocalDateTime(convertToLocalDateTime(data[LOCALE_DATE_TIME_INDEX]));
        user.setSummary(data[SUMMARY_INDEX]);
        user.setText(data[TEXT_INDEX]);
        return user;
    }
}
