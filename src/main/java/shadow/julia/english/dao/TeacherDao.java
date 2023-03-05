package shadow.julia.english.dao;

import shadow.julia.english.properties.ApplicationPropertiesLoader;
import shadow.julia.english.persistence.model.postgres.Teacher;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TeacherDao {

    private final ApplicationPropertiesLoader properties;

    @Inject
    public TeacherDao(ApplicationPropertiesLoader properties) {
        this.properties = properties;
    }

    public void save(Teacher teacher) throws SQLException {
        Properties props = new Properties();
        String url = "jdbc:postgresql://localhost/test";
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "true");
        Connection conn = DriverManager.getConnection(url, props);
    }
}
