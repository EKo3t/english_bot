package shadow.julia.english;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileNotFoundException;

public class ApplicationRunner {

    public static void main(String[] args) throws FileNotFoundException {
        var properties = new ApplicationProperties();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new EnglishBot(properties.getBotToken()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
