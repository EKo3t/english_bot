package shadow.julia.english.persistence.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Teacher {

    private final String name;
    private final String lastName;
}
