package shadow.julia.english;

import org.yaml.snakeyaml.Yaml;
import shadow.julia.english.exception.ApplicationPropertiesNotFoundException;

import java.io.InputStream;
import java.util.Map;

public class ApplicationProperties {

    private static final String FILE_NAME = "application.yml";
    private String botToken;

    public ApplicationProperties() {
    }

    public void init() throws ApplicationPropertiesNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(FILE_NAME);
        if (inputStream == null) {
            throw new ApplicationPropertiesNotFoundException("Can't find file with the name \"" + FILE_NAME + "\"");
        }
        Map<String, Object> obj = yaml.load(inputStream);
        botToken = (String) obj.get("botToken");
    }

    public String getBotToken() {
        return botToken;
    }
}
