package currencyBot;

import java.util.ArrayList;
import java.util.List;

public enum Currency {

    USD("USD", true),
    EUR("EUR", false);

    private String currencyName;
    private boolean currencySelect;
    Currency(String currency, boolean select) {
        this.currencyName = currency;
        this.currencySelect = select;
    }

    public String getCurrencyName() {
        return currencyName;
    }
   
    public static List<Currency> getSelectedCurrencyList() {
        List<Currency> selectedCurr = new ArrayList<>();
        for (Currency currency : Currency.values()) {
            if (currency.currencySelect) {
                selectedCurr.add(currency);
            }
        }
        return selectedCurr;
    }
    public static Currency convertToEnum(String text) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equals(text)) {
                return currency;
            }
        }
        return null;
    }
}
