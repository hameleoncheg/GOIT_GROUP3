package currencyBot;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface CurrencyRateApiService {
    List<RateResponseDto> getRates(List<Currency> curr, int numberAfterComma) throws IOException;

    static BigDecimal RoundToDecimalPlaces(BigDecimal numberToRound, int precisionForRounding){
         return numberToRound.setScale(precisionForRounding, RoundingMode.HALF_UP);
    }

}