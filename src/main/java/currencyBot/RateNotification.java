package currencyBot;
import java.io.*;

import BankUtil.MonoBankCurrencyRateService;
import settings.*;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RateNotification {

    public static void main(String[] args) throws IOException {
        new RateNotification().base();
    }
    public void base() throws IOException { //витягує час з json
        String root = "src/main/resources/settings.json";
        File file = new File(root);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String json = br.readLine();

        //JSONObject jsonObject = new JSONObject(json);
        //System.out.println(new Setting().getNotifTime().getTime());

        System.out.println(getCurrencies(json));
        timer(SetToJson.settings.get("notifTime"), json);

        //System.out.println(jsonObject);
        //System.out.println(jsonObject.toString().contains("notifTime"));

        //String notiTime = jsonObject.getString("notifTime");

        //System.out.println(notiTime);
    }
    private String getTime(String json){ //тимчасовий метод поки я не полагоджу попередній
        int k = 0;
        String[] splitter = json.split(String.valueOf('"'));
        for (int i = 0; i < splitter.length; i++) {
            if (splitter[i].equals("notifTime")){
                k += i + 2;
                return splitter[k];
            }
        }
        return null;
    }

    private String getNumberAfterComa(String json){
        int k = 0;
        String[] splitter = json.split(String.valueOf('"'));
        for (int i = 0; i < splitter.length; i++) {
            if (splitter[i].equals("numberAfterCom")){
                k += i + 2;
                return splitter[k];
            }
        }
        return null;
    }

    private long getChatId(String json){
        int k = 0;
        String[] splitter = json.split(String.valueOf('"'));
        for (int i = 0; i < splitter.length; i++) {
            if (splitter[i].equals("chatId")){
                k += i + 1;
                return Long.parseLong(splitter[k]
                        .replace(":","")
                        .replace(",",""));
            }
        }
        return 0;
    }

    private List<String> getCurrencies(String json){
        int k = 0;
        String toFilter;
        String toArray = "";
        String[] splitter = json.split(String.valueOf(':'));
        toFilter = splitter[splitter.length - 1];
        for (int i = 0; i < toFilter.length(); i++) {
            if (Character.isUpperCase(toFilter.charAt(i)) || toFilter.charAt(i) == ','){
                toArray += toFilter.charAt(i);
            }
        }
        String[] array = toArray.split(",");
        List<String> currencies = new ArrayList<>();
        currencies.addAll(Arrays.asList(array));
        return currencies;
    }

    private int replacer(String needToReplace){ //міняє текстове значення на цифри
        int returner = 0;
        System.out.println(needToReplace);
        if (needToReplace.equalsIgnoreCase("nine")){
            returner += 11;
            System.out.println(returner);
        }
        if (needToReplace.equalsIgnoreCase("ten")){
            returner += 10;
        }
        if (needToReplace.equalsIgnoreCase("eleven")){
            returner += 11;
        }
        if (needToReplace.equalsIgnoreCase("twelve")){
            returner += 12;
        }
        if (needToReplace.equalsIgnoreCase("thirteen")){
            returner += 13;
        }
        if (needToReplace.equalsIgnoreCase("fourteen")){
            returner += 14;

        }
        if (needToReplace.equalsIgnoreCase("fifteen")){
            returner += 15;

        }
        if (needToReplace.equalsIgnoreCase("sixteen")){
            returner += 16;

        }
        if (needToReplace.equalsIgnoreCase("seventeen")){
            returner += 17;

        }
        if (needToReplace.equalsIgnoreCase("eighteen")){
            returner += 18;

        }
        System.out.println(returner);
        return returner;
    }



    public void timer(Setting time, String json){ //порівнює час
        String message;
        new Thread(() -> {
            while(true) {
                if (1 == 1) {
                    if (json.toLowerCase().contains("mono")){
                        //new PrettyResponseConverter().prepareResponse(new MonoBankCurrencyRateService(getCurrencies(json), getNumberAfterComa(json)))
                    }
                    break;
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
