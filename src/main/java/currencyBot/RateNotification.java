package currencyBot;
import java.io.*;

import BankUtil.MonoBankCurrencyRateService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.*;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class RateNotification {

    public static void sendNotify(CurrencyRateBot currencyRateBot) throws IOException, TelegramApiException, InterruptedException {

        List<Setting> myList = new ArrayList<Setting>();
        ///Get settings from json file
        Thread thread = new Thread();
        thread.start();
        // Wait for the thread to finish
        while(true) {

            SetToJson.load();
            System.out.println("Settings on start application");
            // System.out.println(SetToJson.settings);

            List<Setting> list = new ArrayList<Setting>(SetToJson.settings.values());
            for (Setting s : list) {
                System.out.println("List: "+s);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("HH");
            Calendar calendar = Calendar.getInstance();
            System.out.println(formatter.format(calendar.getTime()));
            NotifTime notifTime = NotifTime.convertToEnum(formatter.format(calendar.getTime()));

            boolean needToSend = true;


            myList = list.stream()
                    .filter(item -> item.getNotifTime() == notifTime)
                    .collect(Collectors.toList());
            for (Setting s : myList) {
                currencyRateBot.printMessage(s.getChatId(), currencyRateBot.getInfo(s.getChatId()));
            }
            thread.sleep(60*60*1000);
        }
    }

}
