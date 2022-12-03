package currencyBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRateBot extends TelegramLongPollingBot {

    //
    PrettyResponseConverter converter = new PrettyResponseConverter();

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
                setButtons(message);

                try {
                    execute(message); // Call method to send the message


                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setButtons(SendMessage message){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("buy"));
        keyboardFirstRow.add(new KeyboardButton("sell"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

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
