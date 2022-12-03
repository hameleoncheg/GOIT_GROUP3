package settings;

public class Setting {
    private Long chatId;
    private NumberAfterComa numberAfterComa;
    private NotifTime notifTime;
    private TimeZone timeZone;
   // private Banks selectedBank;
   // private List<Currency> selectedCurrency;
   // private Language language;

      public Setting() {}

    public Setting(Long chatId, NumberAfterComa numberAfterComa, NotifTime notifTime, TimeZone timeZone){
                   //Banks selectedBank, List<Currency> selectedCurrency, Language language,
        this.chatId = chatId;
        this.numberAfterComa = numberAfterComa;
        this.notifTime = notifTime;
        this.timeZone = timeZone;
        // this.language = language;
        //  this.selectedBank = selectedBank;
        // this.selectedCurrency = selectedCurrency;
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

    /*
    public Banks getSelectedBank() {
        return selectedBank;
    }


    public void setSelectedBank(Banks selectedBank) {
        this.selectedBank = selectedBank;
    }

    public List<Currency> getSelectedCurrency() {
        return selectedCurrency;
    }

    public void setSelectedCurrency(List<Currency> selectedCurrency) {
        this.selectedCurrency = selectedCurrency;
    }
    public void setSelectedLanguage(Language language) {
        this.language = language;
    }

    public Language getSelectedLanguage() {
        return language;
    }
*/
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
