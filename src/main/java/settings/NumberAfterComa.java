package settings;

public enum NumberAfterComa {
    TWO (2, "twoPlaces",false),
    THREE (3, "threePlaces",false),
    FOUR (4, "fourPlaces",false);

    private String countAfterComa;
    private int intNumber;
    private boolean select;

    NumberAfterComa(int intNumber, String countAfterComa, boolean select) {
        this.intNumber = intNumber;
        this.countAfterComa = countAfterComa;
        this.select = select;
    }

    public int getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(int intNumber) {
        this.intNumber = intNumber;
    }

    public String getCountAfterComa() {
        return countAfterComa;
    }

    public void setCountAfterComa(String countAfterComa) {
        this.countAfterComa = countAfterComa;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public static NumberAfterComa convertToEnum (String text){
        for (NumberAfterComa decimalPlaces: NumberAfterComa.values()) {
            if (decimalPlaces.getCountAfterComa().equals(text)) {
                return decimalPlaces;
            }
        }
        return null;
    }
}
