package shadow.julia.english.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostgresProperties {

    private String url;
    private String username;
    private String password;
    private boolean useSsl;
}

