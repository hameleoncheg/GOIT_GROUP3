package menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.Buttons;

import java.util.ArrayList;
import java.util.List;

public class MenuStart {
    public static InlineKeyboardMarkup keyboardStart(){
        List<List<InlineKeyboardButton>> keyboardMenuStart = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSRow2 = new ArrayList<>();
        InlineKeyboardButton buttonGetInfo = InlineKeyboardButton.builder()
                .text(Buttons.GET_INFO.getName())
                .callbackData(Buttons.GET_INFO.getNameEN())
                .build();
        InlineKeyboardButton buttonSettings = InlineKeyboardButton.builder()
                .text(Buttons.SETTINGS.getName())
                .callbackData(Buttons.SETTINGS.getNameEN())
                .build();
        keyboardMSRow1.add(buttonGetInfo);
        keyboardMSRow2.add(buttonSettings);
        keyboardMenuStart.add(keyboardMSRow1);
        keyboardMenuStart.add(keyboardMSRow2);
        return InlineKeyboardMarkup.builder().keyboard(keyboardMenuStart).build();
    }
}