package settings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import currencyBot.Currency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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

    public static void load() throws RuntimeException {
             synchronized (monitor) {
            try {
                 ObjectMapper objectMapper = new ObjectMapper();
                 settings = objectMapper.readValue(
                        new File(SETTING_GSON_PATH), new TypeReference<Map<Long, Setting>>() {
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
}
