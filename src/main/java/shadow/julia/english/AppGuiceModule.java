package shadow.julia.english;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AppGuiceModule extends AbstractModule {

    @Provides
    public ApplicationProperties applicationProperties() {
        var properties = new ApplicationProperties();
        properties.init();
        return properties;
    }

    @Provides
    public TelegramBotsApi telegramBotsApi(ApplicationProperties properties) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new EnglishBot(properties.getBotToken()));
        return telegramBotsApi;
    }
}
