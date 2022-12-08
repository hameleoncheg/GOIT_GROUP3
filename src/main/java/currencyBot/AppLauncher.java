package currencyBot;

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
    public static void main(String[] args) {

        try {
            CurrencyRateBot currencyRateBot = CurrencyRateBot.getInstance("currencyRateBot");
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(currencyRateBot);
            RateNotification.sendNotify(currencyRateBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}