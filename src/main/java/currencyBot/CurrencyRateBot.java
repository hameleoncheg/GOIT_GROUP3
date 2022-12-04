package currencyBot;

import menu.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import settings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyRateBot extends TelegramLongPollingBot {
    PrettyResponseConverter converter = new PrettyResponseConverter();
    private static CurrencyRateBot instance;
    public String value;
    private Setting userSettings;
    private final static Object monitor = new Object();
    private CurrencyRateBot(String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
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
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        /*
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("#Monobank") || messageText.equals("#PrivatBank") || messageText.equals("#NBUbank")) {
                CurrencyRateApiService rateService = getRateService(update.getMessage().getText());
                SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("Курс банка "+messageText+": " + "\n" +
                        converter.prepareResponse(
                                update.getMessage().getText(),
                                rateService.getRates()
                        )

                );
                //setButtons(message);

                try {
                    execute(message); // Call method to send the message


                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    private void handleQuery(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        synchronized (monitor) {
            if (SetToJson.settings.get(chatId) == null) {
                userSettings = new Setting(chatId, NumberAfterComa.TWO, Banks.PRIVAT,
                        Currency.getSelectedCurrencyList(), NotifTime.NINE, TimeZone.UTC_THREE);
            } else {
                userSettings = SetToJson.settings.get(chatId);
            }
        }
    }
    private void handleMessage(Message message) throws TelegramApiException {
        long chatId = message.getChatId();
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
    public void checkMainMenu(CallbackQuery buttonQuery) throws TelegramApiException {
        long chatId = buttonQuery.getMessage().getChatId();
        String dataButtonQuery = buttonQuery.getData();
        if (Buttons.convertToEnum(dataButtonQuery) != null){
            switch (Buttons.convertToEnum(dataButtonQuery)) {
                case GET_INFO:
                    printMessage(chatId, SetToJson.getInfo(chatId));
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
    }

        private CurrencyRateApiService getRateService(String bank){
        switch (bank) {
            case "#Monobank":
                return new MonoBankCurrencyRateService();
            case "#PrivatBank":
                return new PrivatBankCurrencyRateService();
            case "#NBUbank":
                return new NbuCurrencyRateService();

                   }
                   return new NbuCurrencyRateService();
    }
}
