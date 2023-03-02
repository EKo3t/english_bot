package shadow.julia.english;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ApplicationProperties {

    private static final String FILE_NAME = "application.yml";
    private final String botToken;

    public ApplicationProperties() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(FILE_NAME);
        if (inputStream == null) {
            throw new FileNotFoundException("Can't find file with the name \"" + FILE_NAME + "\"");
        }
        Map<String, Object> obj = yaml.load(inputStream);
        botToken = (String) obj.get("botToken");
    }

    public String getBotToken() {
        return botToken;
    }
}
