package shadow.julia.english;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import shadow.julia.english.dao.TeacherDao;
import shadow.julia.english.properties.ApplicationPropertiesLoader;
import shadow.julia.english.properties.PostgresProperties;
import shadow.julia.english.properties.TelegramProperties;

public class AppGuiceModule extends AbstractModule {

    @Provides
    public ApplicationPropertiesLoader applicationProperties() {
        var properties = new ApplicationPropertiesLoader();
        properties.init();
        return properties;
    }

    @Provides
    public TelegramBotsApi telegramBotsApi(TelegramProperties properties) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new EnglishBot(properties.getBotToken()));
        return telegramBotsApi;
    }

    @Provides
    public PostgresProperties postgresProperties(ApplicationPropertiesLoader applicationPropertiesLoader) {
        applicationPropertiesLoader.throwIfAnyNull("db.postgres", "username", "password", "url");
        var userName = applicationPropertiesLoader.get("db.postgres.username");
        var password = applicationPropertiesLoader.get("db.postgres.password");
        var url = applicationPropertiesLoader.get("db.postgres.url");
        var useSsl = applicationPropertiesLoader.getAsBoolean("db.postgres.useSsl");
        return new PostgresProperties(userName, password, url, useSsl);
    }

    @Provides
    public TelegramProperties telegramProperties(ApplicationPropertiesLoader propertiesLoader) {
        propertiesLoader.throwIfAnyNull("telegram", "botToken");
        var botToken = propertiesLoader.get("telegram.botToken");
        return new TelegramProperties(botToken);
    }

    @Provides
    public TeacherDao teacherDao(ApplicationPropertiesLoader properties) {
        return new TeacherDao(properties);
    }
}
