package menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.Buttons;
import currencyBot.Currency;
import settings.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuSettings {
    public InlineKeyboardMarkup keyboardSettings(Setting setting){
        String selectedCurr = setting.getSelectedCurrency().stream()
                .map(Currency::getCurrencyName)
                .collect(Collectors.joining(", ", "(", ")"));

        List<List<InlineKeyboardButton>> keyboardMenuSettings = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow5 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow6 = new ArrayList<>();
        InlineKeyboardButton buttonNumAfterComa = InlineKeyboardButton.builder()
                .text(Buttons.NUM_DECIMAL_PLACES.getName() + " (" + setting.getNumberAfterComa() + ")")
                .callbackData(Buttons.NUM_DECIMAL_PLACES.getNameEN())
                .build();
        InlineKeyboardButton buttonBank = InlineKeyboardButton.builder()
                .text(Buttons.BANK.getName() + " (" + setting.getSelectedBank().getBankNameUA() + ")")
                .callbackData(Buttons.BANK.getNameEN())
                .build();
        InlineKeyboardButton buttonCurrency = InlineKeyboardButton.builder()
                .text(Buttons.CURRENCY.getName() + selectedCurr)
                .callbackData(Buttons.CURRENCY.getNameEN())
                .build();
        String NotificationTimeSet = setting.getNotifTime().getTime() == 0 ? "OFF" :
                String.valueOf(setting.getNotifTime().getTime());
        InlineKeyboardButton buttonNotificationTime = InlineKeyboardButton.builder()
                .text(Buttons.NOTIFICATION.getName() + " (" + NotificationTimeSet + ")")
                .callbackData(Buttons.NOTIFICATION.getNameEN())
                .build();
        InlineKeyboardButton buttonTimeZone= InlineKeyboardButton.builder()
                .text(Buttons.ZONEID.getName() + " (" + setting.getTimeZone().getNameZone() + ")")
                .callbackData(Buttons.ZONEID.getNameEN())
                .build();
        InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_START.getName())
                .callbackData(Buttons.BACK_TO_START.getNameEN())
                .build();

        keyboardMSetRow1.add(buttonNumAfterComa);
        keyboardMSetRow2.add(buttonBank);
        keyboardMSetRow3.add(buttonCurrency);
        keyboardMSetRow4.add(buttonNotificationTime);
        keyboardMSetRow5.add(buttonTimeZone);
        keyboardMSetRow6.add(buttonBack);
        keyboardMenuSettings.add(keyboardMSetRow1);
        keyboardMenuSettings.add(keyboardMSetRow2);
        keyboardMenuSettings.add(keyboardMSetRow3);
        keyboardMenuSettings.add(keyboardMSetRow4);
        keyboardMenuSettings.add(keyboardMSetRow5);
        keyboardMenuSettings.add(keyboardMSetRow6);

        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuSettings).build();
    }
}
