package settings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import currencyBot.Currency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetToJson {
    public static Map<Long, Setting> settings = new HashMap<>();
    private static final Gson settingGson = new Gson();
    private static final Object monitor = new Object();
    private static final String SETTING_GSON_PATH = "src/main/resources/settings.json";

    static ExecutorService service = Executors.newSingleThreadExecutor();

        public static File fileSettingsGsonCheck() {
            File settingGsonFile = new File(SETTING_GSON_PATH);
            if (!settingGsonFile.exists()) {
                System.out.println("Create Path for Gson file Settings - " + settingGsonFile.getParentFile().mkdirs());
                try {
                    System.out.println("Create new Gson file Settings - " + settingGsonFile.createNewFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return settingGsonFile;
        }

    public void load() {
             synchronized (monitor) {
            try {
                UserHashDTO.userHashDTO = new ObjectMapper().readValue(fileSettingsGsonCheck(),
                        new TypeReference<Map<Long, UserSetDTO>>() {
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        synchronized (monitor) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSettingsGsonCheck()))) {
                writer.write(settingGson.toJson(settings));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void converter(UserHashDTO userHashDTO) {
        synchronized (monitor) {
            Map<Long, UserSetDTO> inputMap = userHashDTO.userHashDTO;
            Map<Long, Setting> outputMap = settings;
            inputMap.forEach((k, v) -> {
                Setting outputSetting = new Setting();

                outputSetting.setChatId(v.getChatId());
                outputSetting.setNumberAfterComa(parseNumberAfterComa(v.getNumberAfterComa()));
          //      outputSetting.setSelectedBank(parseSelectedBank(v.getSelectedBank()));
           //     outputSetting.setSelectedCurrency(parseCurrency(v.getSelectedCurrency()));
                outputSetting.setNotifTime(parseNotifTime(v.getNotifTime()));
                outputSetting.setTimeZone(parseTimeZone(v.getTimeZone()));
                outputMap.put(v.getChatId(), outputSetting);
            });
        }
    }

    private NumberAfterComa parseNumberAfterComa(String inputStrNumOfDec) {
        for (NumberAfterComa value : NumberAfterComa.values()) {
            if (inputStrNumOfDec.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

    private Banks parseSelectedBank(String inputStrBank) {
        for (Banks value : Banks.values()) {
            if (inputStrBank.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

    private List<Currency> parseCurrency(List<String> inputListStrCurrency) {
        List<Currency> result = new ArrayList<>();
        for (Currency value : Currency.values()) {
            for (String oneCurrency : inputListStrCurrency) {
                if (oneCurrency.equals(value.name())) {
                    result.add(value);
                }
            }

        }
        return result;
    }

    private NotifTime parseNotifTime(String inputStrNotificationTime) {
        for (NotifTime value : NotifTime.values()) {
            if (inputStrNotificationTime.equals(value.name())) {
                return value;
            }
        }
        return null;
    }

    private TimeZone parseTimeZone(String inputStrZoneId) {
        for (TimeZone value : TimeZone.values()) {
            if (inputStrZoneId.equals(value.name())) {
                return value;
            }
        }
        return null;
    }
}
