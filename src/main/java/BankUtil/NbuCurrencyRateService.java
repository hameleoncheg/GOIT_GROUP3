package BankUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import currencyBot.Currency;
import currencyBot.CurrencyRateApiService;
import currencyBot.RateResponseDto;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class NbuCurrencyRateService implements CurrencyRateApiService {

    String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    private Gson gson = new Gson();

    List<Currency> curr;
    int numberAfterComma;

    public NbuCurrencyRateService(List<Currency> curr, int numberAfterComma) {
        this.curr = curr;
        this.numberAfterComma = numberAfterComma;
    }

    @Override
    public List<RateResponseDto> getRates(List<Currency> curr, int numberAfterComma) throws IOException {
        String text = null;
        text = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        List<NbuCurrencyResponseDto> responseDtos = convertResponse(text);
        return responseDtos.stream()
                .map(item -> {
                    RateResponseDto dto = new RateResponseDto();
                    dto.setCurrencyTo(item.getCc());
                    dto.setRateBuy(item.getRate());
                    dto.setRateSell(item.getRate());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    private List<NbuCurrencyResponseDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, NbuCurrencyResponseDto.class)
                .getType();
        List<NbuCurrencyResponseDto> responseDtos = gson.fromJson(response, type);
        return  responseDtos.stream()
                .filter(item -> Currency.USD.equals(item.getCc()) || Currency.EUR.equals(item.getCc()))
                .collect(Collectors.toList());
    }
}
