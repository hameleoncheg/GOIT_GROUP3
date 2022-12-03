package currencyBot;

import java.math.BigDecimal;

public class MonoBankCurrencyResponseDto {
    private Integer currencyCodeA;
    private Integer currencyCodeB;

    private Currency currencyA;
    private Currency currencyB;
    private BigDecimal rateBuy;
    private BigDecimal rateSell;

    public Integer getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(Integer currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public Integer getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(Integer currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(BigDecimal rateBuy) {
        this.rateBuy = rateBuy;
    }

    public Currency getCurrencyA() {
        return currencyA;
    }

    public void setCurrencyA(Currency currencyA) {
        this.currencyA = currencyA;
    }

    public Currency getCurrencyB() {
        return currencyB;
    }

    public void setCurrencyB(Currency currencyB) {
        this.currencyB = currencyB;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public void setRateSell(BigDecimal rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public String toString() {
        return "MonoBankCurrencyResponseDto{" +
                "ccy=" + currencyCodeA +
                ", base_ccy=" + currencyCodeB +
                ", buy=" + rateBuy +
                ", sale=" + rateSell +
                '}';
    }
}
