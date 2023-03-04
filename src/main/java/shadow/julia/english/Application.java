package shadow.julia.english;

import com.google.inject.Guice;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Application {

    public static void main(String[] args) {
        var injector = Guice.createInjector(new AppGuiceModule());
        var bots = injector.getInstance(TelegramBotsApi.class);
    }
}
