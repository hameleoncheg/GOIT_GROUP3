package currencyBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import settings.SetToJson;

import java.io.IOException;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            CurrencyRateBot currencyRateBot = CurrencyRateBot.getInstance("currencyRateBot");
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(currencyRateBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
