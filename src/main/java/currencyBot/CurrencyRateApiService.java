package currencyBot;
import java.io.IOException;
import java.util.List;

public interface CurrencyRateApiService {
    List<RateResponseDto> getRates() throws IOException;
}
