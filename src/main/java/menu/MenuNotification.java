package menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.*;

import java.util.ArrayList;
import java.util.List;

public class MenuNotification {
    public InlineKeyboardMarkup keyboardNotification(long chatId) {
        Setting userSetting = SetToJson.settings.get(chatId);
        NotifTime selectedNotificationTime = userSetting.getNotifTime();
        List<List<InlineKeyboardButton>> keyboardMenuNotification = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        InlineKeyboardButton buttonNotificationTime9 = InlineKeyboardButton.builder()
                .text(NotifTime.NINE.getTime() + getButtonStatus(NotifTime.NINE, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.NINE.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime10 = InlineKeyboardButton.builder()
                .text(NotifTime.TEN.getTime() + getButtonStatus(NotifTime.TEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.TEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime11 = InlineKeyboardButton.builder()
                .text(NotifTime.ELEVEN.getTime() + getButtonStatus(NotifTime.ELEVEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.ELEVEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime12 = InlineKeyboardButton.builder()
                .text(NotifTime.TWELVE.getTime() + getButtonStatus(NotifTime.TWELVE, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.TWELVE.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime13 = InlineKeyboardButton.builder()
                .text(NotifTime.THIRTEEN.getTime() + getButtonStatus(NotifTime.THIRTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.THIRTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime14 = InlineKeyboardButton.builder()
                .text(NotifTime.FOURTEEN.getTime() + getButtonStatus(NotifTime.FOURTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.FOURTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime15 = InlineKeyboardButton.builder()
                .text(NotifTime.FIFTEEN.getTime() + getButtonStatus(NotifTime.FIFTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.FIFTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime16 = InlineKeyboardButton.builder()
                .text(NotifTime.SIXTEEN.getTime() + getButtonStatus(NotifTime.SIXTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.SIXTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime17 = InlineKeyboardButton.builder()
                .text(NotifTime.SEVENTEEN.getTime() + getButtonStatus(NotifTime.SEVENTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.SEVENTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonNotificationTime18 = InlineKeyboardButton.builder()
                .text(NotifTime.EIGHTEEN.getTime() + getButtonStatus(NotifTime.EIGHTEEN, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.EIGHTEEN.getTime()))
                .build();
        InlineKeyboardButton buttonTurnOfNotification = InlineKeyboardButton.builder()
                .text("OFF" + getButtonStatus(NotifTime.SWICH_OFF, selectedNotificationTime))
                .callbackData(String.valueOf(NotifTime.SWICH_OFF.getTime()))
                .build();
        InlineKeyboardButton buttonHome = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_START.getName())
                .callbackData(Buttons.BACK_TO_START.getNameEN())
                .build();
        InlineKeyboardButton buttonBack = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_SETTINGS.getName())
                .callbackData(Buttons.BACK_TO_SETTINGS.getNameEN())
                .build();
        keyboardMSetRow1.add(buttonNotificationTime9);
        keyboardMSetRow1.add(buttonNotificationTime10);
        keyboardMSetRow1.add(buttonNotificationTime11);
        keyboardMSetRow1.add(buttonNotificationTime12);
        keyboardMSetRow1.add(buttonNotificationTime13);
        keyboardMSetRow2.add(buttonNotificationTime14);
        keyboardMSetRow2.add(buttonNotificationTime15);
        keyboardMSetRow2.add(buttonNotificationTime16);
        keyboardMSetRow2.add(buttonNotificationTime17);
        keyboardMSetRow2.add(buttonNotificationTime18);
        keyboardMSetRow3.add(buttonTurnOfNotification);
        keyboardMSetRow4.add(buttonHome);
        keyboardMSetRow4.add(buttonBack);
        keyboardMenuNotification.add(keyboardMSetRow1);
        keyboardMenuNotification.add(keyboardMSetRow2);
        keyboardMenuNotification.add(keyboardMSetRow3);
        keyboardMenuNotification.add(keyboardMSetRow4);

        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuNotification).build();
    }

    private String getButtonStatus(NotifTime current, NotifTime selected) {
        if (current == selected) {
            return "✅";
        }
        return "";
    }

}
