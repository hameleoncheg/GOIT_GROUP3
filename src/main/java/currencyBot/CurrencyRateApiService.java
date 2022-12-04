package currencyBot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface CurrencyRateApiService {
    List<RateResponseDto> getRates();

    static BigDecimal RoundToDecimalPlaces(BigDecimal numberToRound, int precisionForRounding){
         return numberToRound.setScale(precisionForRounding, RoundingMode.HALF_UP);
    }

}