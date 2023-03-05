package shadow.julia.english.properties;

import org.yaml.snakeyaml.Yaml;
import shadow.julia.english.exception.ApplicationPropertiesNotFoundException;
import shadow.julia.english.exception.EmptyPropertyException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class ApplicationPropertiesLoader {

    private static final String FILE_NAME = "application.yml";
    private Map<String, Object> values;

    public ApplicationPropertiesLoader() {
    }

    public void init() throws ApplicationPropertiesNotFoundException {
        try (InputStream appPropStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (appPropStream != null) {
                Yaml yaml = new Yaml();
                values = yaml.load(appPropStream);
            } else {
                throw new ApplicationPropertiesNotFoundException("Application properties not found \"" + FILE_NAME + "\"");
            }
        } catch (IOException ex) {
            throw new ApplicationPropertiesNotFoundException("Exception trying to open resource \"" + FILE_NAME + "\"");
        }
    }

    public String get(String path) {
        String[] keys = path.split("\\.");
        var values = this.values;
        for (int i = 0; i < keys.length - 1; i++)
            values = (Map<String, Object>) values.get(keys[i]);

        return (String) values.get(keys[keys.length - 1]);
    }

    public boolean getAsBoolean(String path) {
        return Boolean.parseBoolean((String) values.get(path));
    }

    public void throwIfAnyNull(String basePath, String... paths) {
        String[] nullProperties = Arrays.stream(paths)
            .map(path -> basePath + '.' + path)
            .filter(path -> get(path) == null)
            .toArray(String[]::new);
        if (nullProperties.length != 0)
            throw new EmptyPropertyException(nullProperties);
    }

}
