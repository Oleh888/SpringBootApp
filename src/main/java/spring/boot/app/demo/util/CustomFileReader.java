package spring.boot.app.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import spring.boot.app.demo.exceptions.DataProcessingException;

@Component
public class CustomFileReader {
    private static final Logger LOGGER = Logger.getLogger(CustomFileReader.class);

    public List<String> getAll(String directory) {
        List<String> allLines = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
            allLines = Files.readAllLines(Paths.get(directory));
            LOGGER.info("Information from file with directory " + directory + "was read");
        } catch (IOException e) {
            throw new DataProcessingException("Can not read from file with directory " + directory, e);
        }
        return allLines;
    }
}
