package currencyBot;

import BankUtil.MonoBankCurrencyRateService;
import BankUtil.NbuCurrencyRateService;
import BankUtil.PrivatBankCurrencyRateService;
import menu.MenuStart;
import menu.MenuTimeZone;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.*;
import menu.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CurrencyRateBot extends TelegramLongPollingBot {
    PrettyResponseConverter converter = new PrettyResponseConverter();
    private static CurrencyRateBot instance;
    public String value;
    private Setting userSettings;
    private final static Object monitor = new Object();

    public static Map<Long, Setting> settings = new HashMap<>();

    static ExecutorService service = Executors.newSingleThreadExecutor();

    CurrencyRateBot(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                while(true){
                    try {
                        Notification.time();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }}).start();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static CurrencyRateBot getInstance(String value) {
        if (instance == null) {
            instance = new CurrencyRateBot(value);
        }
        return instance;
    }
    @Override
    public String getBotUsername() {
        return Constants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (update.hasCallbackQuery()) {
            try {
                handleQuery(update.getCallbackQuery());
            } catch (TelegramApiException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleQuery(CallbackQuery buttonQuery) throws TelegramApiException, IOException {
        long chatId = buttonQuery.getMessage().getChatId();
        synchronized (monitor) {
            if (SetToJson.settings.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberAfterComa.TWO, Banks.PRIVAT,
                        Currency.getSelectedCurrencyList(), NotifTime.NINE, TimeZone.UTC_THREE);
            } else {
                userSettings = SetToJson.settings.get(chatId);
            }
        }
        checkMainMenu(buttonQuery);
    }
    private void handleMessage(Message message) throws TelegramApiException {
        long chatId = message.getChatId();
        SetToJson.load();
        synchronized (monitor) {
            if (SetToJson.settings.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberAfterComa.TWO, Banks.PRIVAT,
                        Currency.getSelectedCurrencyList(), NotifTime.NINE, TimeZone.UTC_THREE);
            } else {
                userSettings = SetToJson.settings.get(chatId);
            }
        }
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity;
            commandEntity = message.getEntities().stream()
                    .filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText()
                        .substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals(Buttons.START.getNameEN())) {
                    printMessage(chatId, MenuStart.keyboardStart(),
                            "Ласкаво просимо.Цей бот дозволить відслідкувати актуальні курси валют.");
                    synchronized (monitor) {
                        SetToJson.settings.put(chatId, userSettings);
                    }
                }
            }
        } else {
            printMessage(chatId, "Будь ласка впишіть /start або натисніть кнопку.");
        }
    }
    private void printMessage(Long chatID, InlineKeyboardMarkup keyboard, String text)
            throws TelegramApiException {
        execute(SendMessage.builder()
                .text(text)
                .chatId(chatID)
                .replyMarkup(keyboard)
                .build());
    }

    public void printMessage(Long chatID, String messageText) throws TelegramApiException {
        execute(SendMessage.builder()
                .text(messageText)
                .chatId(chatID)
                .build());
    }

    private void updateMessage(CallbackQuery buttonQuery, InlineKeyboardMarkup keyboard)
            throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        int messageId = buttonQuery.getMessage().getMessageId();
        execute(EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(keyboard)
                .build());
    }

    private static CurrencyRateApiService getRateService(Banks bank, List<Currency> curr, int numberAfterComma){
        switch (bank) {
            case MONO:
                return new MonoBankCurrencyRateService();
            case PRIVAT:
                return new PrivatBankCurrencyRateService();
            case NBU:
                return new NbuCurrencyRateService();

                   }
                   return new NbuCurrencyRateService();
    }

    public static String getInfo (Long chatId) throws IOException {

       // service.execute(new SaveSets());
       // StringBuilder messageToUser = new StringBuilder();

        Setting userSetting = SetToJson.settings.get(chatId);
        String bankName = userSetting.getSelectedBank().getBankNameUA();


        int numberAfterComa = userSetting.getNumberAfterComa();
        List<Currency> currencies = userSetting.getSelectedCurr();
        Banks bank = userSetting.getSelectedBank();

        CurrencyRateApiService rateService = getRateService(bank, currencies, numberAfterComa);

        PrettyResponseConverter converter = new PrettyResponseConverter();

        StringBuilder messageToUser = new StringBuilder();
        messageToUser.append(bankName).append("\n");
        messageToUser.append(converter.prepareResponse(rateService.getRates(currencies, numberAfterComa)));


//        for (Currency currency : currencies) {
//            messageToUser.append("Курс купівлі ")
//                    .append(currency.getCurrencyName())
//                    .append(" - ")
//                    .append(rateService.getRates(currencies, numberAfterComa))
//                    //.append(bankInfo.getBuyRate(currency) == 0 ? "немає купівлі" :
//                    //       format("%." + numberAfterComa + "f" , bankInfo.getBuyRate(currency)))
//                    .append("\n");
//            messageToUser.append("Курс продажу ")
//                    .append(currency.getCurrencyName())
//                    .append(" - ")
//                    // .append(bankInfo.getSellRate(currency) == 0 ? "немає продажу" :
//                    //         format("%." + numberAfterComa + "f" , bankInfo.getSellRate(currency)))
//                    .append("\n");
//        }
        return messageToUser.toString();
    }

    public void checkMainMenu(CallbackQuery buttonQuery) throws TelegramApiException, IOException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Buttons.convertToEnum(dataButtonQuery) != null){
            switch (Buttons.convertToEnum(dataButtonQuery)) {
                case GET_INFO:
                    printMessage(chatId, getInfo(chatId));
                    printMessage(chatId, MenuStart.keyboardStart(), "Щоб отримати інфо натисність кнопку");
                    break;
                case SETTINGS:
                    printMessage(chatId, MenuSettings.keyboardSettings(SetToJson.settings.get(chatId)), "Виберіть налаштування");
                    break;
                case BACK_TO_START:
                    printMessage(chatId, MenuStart.keyboardStart(), "Щоб отримати інфо натисність кнопку");
                    break;
                case NUM_DECIMAL_PLACES:
                    updateMessage(buttonQuery, MenuNumbAfterComa.keyboardNumbAfterComa(chatId));
                    break;
                case BANK:
                    updateMessage(buttonQuery, MenuBank.keyboardBanks(chatId));
                    break;
                case CURRENCY:
                    updateMessage(buttonQuery, MenuCurrency.keyboardCurrency(chatId));
                    break;
                case NOTIFICATION:
                    updateMessage(buttonQuery, MenuNotification.keyboardNotification(chatId));
                    break;
                case ZONEID:
                    updateMessage(buttonQuery, MenuTimeZone.keyboardTimeZone(chatId));
                    break;
            }
        }
        //Add/delete curr from settings and refresh menu currencies
        Currency curr = Currency.convertToEnum(dataButtonQuery);
        if (curr != null){
            Setting userSetting = SetToJson.settings.get(chatId);
            userSetting.addRemoveCurrency(curr);
            updateMessage(buttonQuery, MenuCurrency.keyboardCurrency(chatId));
            SetToJson.save();
        }

        //Add/delete bank from settings and refresh menu banks
        Banks bank = Banks.convertToEnum(dataButtonQuery);
        if (bank != null){
            Setting userSetting = SetToJson.settings.get(chatId);
            userSetting.setSelectedBank(bank);
            updateMessage(buttonQuery, MenuBank.keyboardBanks(chatId));
            SetToJson.save();
        }

        //Add/delete bank from settings and refresh menu numberAfterComa
        NumberAfterComa numberAfterComa = NumberAfterComa.convertToEnum(dataButtonQuery);
        if (numberAfterComa != null){
            Setting userSetting = SetToJson.settings.get(chatId);
            userSetting.setNumberAfterComa(numberAfterComa);
            updateMessage(buttonQuery, MenuNumbAfterComa.keyboardNumbAfterComa(chatId));
            SetToJson.save();
        }

        //Add/delete bank from settings and refresh menu banks
        NotifTime notifTime = NotifTime.convertToEnum(dataButtonQuery);
        if (notifTime != null){
            Setting userSetting = SetToJson.settings.get(chatId);
            userSetting.setNotifTime(notifTime);
            updateMessage(buttonQuery, MenuNotification.keyboardNotification(chatId));
            SetToJson.save();
        }

    }


}
