package hello.springmvc.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Getter
 * @Setter
 * @ToString
 * @EqualsAndHashCode
 * @RequiredArgsConstructor
 */
@Data
public class HelloData {

    private String username;
    private int age;
}
