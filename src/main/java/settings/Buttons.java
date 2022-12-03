package settings;

public enum Buttons {
    START("Старт", "/start"),
    GET_INFO("Отримати інфо", "Get info"),
    SETTINGS("Налаштування", "Settings"),
    NUM_DECIMAL_PLACES("Кількість знаків після коми", "Number of decimal places"),
    BANK("Банк", "Bank"),
    CURRENCY("Валюта", "Currency"),
    NOTIFICATION("Час сповіщення", "Notification"),
    ZONEID("Часовий пояс", "Time zone"),
    BACK_TO_SETTINGS("Повернутися", "Settings"),
    BACK_TO_START("Головне меню", "BACK_TO_START");

    private String buttonsNameUA;
    private String buttonsNameEN;
    Buttons(String buttonsNameUA, String buttonsNameEN) {
        this.buttonsNameUA = buttonsNameUA;
        this.buttonsNameEN = buttonsNameEN;
    }
    public String getNameUA() {
        return buttonsNameUA;
    }
    public String getNameEN() {
        return buttonsNameEN;
    }
    public static Buttons convertToEnum(String text) {
        for (Buttons button : Buttons.values()) {
            if (button.getNameEN().equals(text)) {
                return button;
            }
        }
        return null;
    }
}