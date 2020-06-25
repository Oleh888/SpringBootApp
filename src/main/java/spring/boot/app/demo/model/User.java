package spring.boot.app.demo.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private Product product;
    private String id;
    private String profileName;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private LocalDateTime localDateTime;
    private String summary;
    private String text;
}
