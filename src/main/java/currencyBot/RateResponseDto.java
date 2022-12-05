package currencyBot;

import java.math.BigDecimal;
import java.util.Objects;

public class RateResponseDto {
    private BigDecimal USD_buy;
    private BigDecimal USD_sell;
    private BigDecimal EUR_buy;
    private BigDecimal EUR_sell;


    public void setUSD_buy(BigDecimal USD_buy) {
        this.USD_buy = USD_buy;
    }

    public void setUSD_sell(BigDecimal USD_sell) {
        this.USD_sell = USD_sell;
    }

    public void setEUR_buy(BigDecimal EUR_buy) {
        this.EUR_buy = EUR_buy;
    }

    public void setEUR_sell(BigDecimal EUR_sell) {
        this.EUR_sell = EUR_sell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateResponseDto bank = (RateResponseDto) o;
        return Objects.equals(USD_buy, bank.USD_buy)
                && Objects.equals(USD_sell, bank.USD_sell)
                && Objects.equals(EUR_buy, bank.EUR_buy)
                && Objects.equals(EUR_sell, bank.EUR_sell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(USD_buy, USD_sell, EUR_buy, EUR_sell);
    }

    public BigDecimal getRateBuy(Currency currency) {
        switch (currency) {
            case EUR:
                return this.EUR_buy;
            case USD:
                return this.USD_buy;
        }
        return null;
    }

    public BigDecimal getRateSell(Currency currency) {
        switch (currency) {
            case EUR:
                return this.EUR_sell;
            case USD:
                return this.USD_sell;
        }
        return null;
    }


    @Override
    public String toString() {
        return "RateResponseDto{" +
                ", USD_buy=" + USD_buy +
                ", USD_sell=" + USD_sell +
                ", EUR_buy=" + EUR_buy +
                ", EUR_sell=" + EUR_sell +
                '}';
    }
}
