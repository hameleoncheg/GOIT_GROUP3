package menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import settings.*;

import java.util.ArrayList;
import java.util.List;


public class MenuNumbAfterComa {
    public static InlineKeyboardMarkup keyboardNumbAfterComa(long chatId) {
        Setting userSetting = SetToJson.settings.get(chatId);
        int selectedNumDecPlaces = userSetting.getNumberAfterComa();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardMSetRow4 = new ArrayList<>();
        InlineKeyboardButton buttonNumberOfDecimalPlaces2 = InlineKeyboardButton.builder()
                .text(NumberAfterComa.TWO.getIntNumber() + getButtonStatus(NumberAfterComa.TWO.getIntNumber(), selectedNumDecPlaces))
                .callbackData(NumberAfterComa.TWO.getCountAfterComa())
                .build();
        InlineKeyboardButton buttonNumberOfDecimalPlaces3 = InlineKeyboardButton.builder()
                .text(NumberAfterComa.THREE.getIntNumber() + getButtonStatus(NumberAfterComa.THREE.getIntNumber(), selectedNumDecPlaces))
                .callbackData(NumberAfterComa.THREE.getCountAfterComa())
                .build();
        InlineKeyboardButton buttonNumberOfDecimalPlaces4 = InlineKeyboardButton.builder()
                .text(NumberAfterComa.FOUR.getIntNumber() + getButtonStatus(NumberAfterComa.FOUR.getIntNumber(), selectedNumDecPlaces))
                .callbackData(NumberAfterComa.FOUR.getCountAfterComa())
                .build();
        InlineKeyboardButton buttonHome = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_START.getName())
                .callbackData(Buttons.BACK_TO_START.getNameEN())
                .build();
        InlineKeyboardButton buttonBackToSetting = InlineKeyboardButton.builder()
                .text(Buttons.BACK_TO_SETTINGS.getName())
                .callbackData(Buttons.BACK_TO_SETTINGS.getNameEN())
                .build();
        keyboardMSetRow1.add(buttonNumberOfDecimalPlaces2);
        keyboardMSetRow2.add(buttonNumberOfDecimalPlaces3);
        keyboardMSetRow3.add(buttonNumberOfDecimalPlaces4);
        keyboardMSetRow4.add(buttonHome);
        keyboardMSetRow4.add(buttonBackToSetting);
        keyboard.add(keyboardMSetRow1);
        keyboard.add(keyboardMSetRow2);
        keyboard.add(keyboardMSetRow3);
        keyboard.add(keyboardMSetRow4);

        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    private static String getButtonStatus(int current, int selected) {
        if (current == selected) {
            return "✅";
        }
        return "";
    }
}
