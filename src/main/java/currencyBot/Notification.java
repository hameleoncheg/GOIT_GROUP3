package currencyBot;

import settings.SetToJson;
import settings.Setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Notification extends CurrencyRateBot{
    Notification(String value) {
        super(value);
    }

    public static void time() throws InterruptedException {

        Thread.sleep(5000);
        SetToJson.load();
        System.out.println(new Setting().toString());
    }
}
