package settings;
import currencyBot.Currency;

import java.util.List;

public class Setting {
    private Long chatId;
    private NumberAfterComa numberAfterComa;
    private NotifTime notifTime;
    private TimeZone timeZone;
    private Banks selectedBank;
    private List<Currency> selectedCurr;


    public Setting() { }

    public Setting(Long chatId, NumberAfterComa numberAfterComa, Banks selectedBank,
                   List<Currency> selectedCurr, NotifTime notifTime, TimeZone timeZone) {
        this.chatId = chatId;
        this.numberAfterComa = numberAfterComa;
        this.notifTime = notifTime;
        this.timeZone = timeZone;
        this.selectedBank = selectedBank;
        this.selectedCurr = selectedCurr;
    }


    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getChatId() {
        return chatId;
    }

    public int getNumberAfterComa() {
        return numberAfterComa.getIntNumber();
    }

    public void setNumberAfterComa(NumberAfterComa numberAfterComa) {
        this.numberAfterComa = numberAfterComa;
    }


    public Banks getSelectedBank() {
        return selectedBank;
    }


    public void setSelectedBank(Banks selectedBank) {
        this.selectedBank = selectedBank;
    }

    public List<Currency> getSelectedCurrency() {
        return selectedCurr;
    }

    public void setSelectedCurrency(List<Currency> selectedCurr) {
        this.selectedCurr = selectedCurr;
    }

    public void addRemoveCurrency(Currency curr) {
        if (selectedCurr.contains(curr)) {
            selectedCurr.remove(curr);
        } else {
            selectedCurr.add(curr);
        }
    }

    public NotifTime getNotifTime() {
        return notifTime;
    }

    public void setNotifTime(NotifTime notifTime) {
        this.notifTime = notifTime;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone zoneId) {
        this.timeZone = zoneId;
    }
}
