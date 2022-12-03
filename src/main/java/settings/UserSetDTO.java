package settings;

import java.util.List;

public class UserSetDTO {
    private Long chatId;
    private String numberAfterComa;
    private String notifTime;
    private String timeZone;
    private String selectBank;
    private String textInfo;
    private List<String> selectCurrency;

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setNumberAfterComa(String numberAfterComa) {
        this.numberAfterComa = numberAfterComa;
    }

    public void setNotifTime(String notifTime) {
        this.notifTime = notifTime;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setSelectBank(String selectBank) {
        this.selectBank = selectBank;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    public void setSelectCurrency(List<String> selectCurrency) {
        this.selectCurrency = selectCurrency;
    }
}
