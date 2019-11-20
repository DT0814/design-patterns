package observed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@AllArgsConstructor
@ToString
public class Article {
    private String text;
    private Instant releaseTime;

}
