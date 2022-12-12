import currencyBot.CurrencyRateBot;
import currencyBot.RateNotification;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import settings.NotifTime;
import settings.SetToJson;
import settings.Setting;

import javax.management.Notification;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class AppLauncher {
    public static void main(String[] args) throws TelegramApiException, IOException, InterruptedException {

            CurrencyRateBot currencyRateBot = CurrencyRateBot.getInstance("currencyRateBot");
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(currencyRateBot);
            RateNotification.sendNotify(currencyRateBot);
      }
}