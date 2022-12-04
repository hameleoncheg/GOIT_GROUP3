package currencyBot;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonoBankCurrencyRateService implements CurrencyRateApiService {
    String url = "https://api.monobank.ua/bank/currency";
    private Gson gson = new Gson();

    public List<RateResponseDto> getRates(){
        String text = null;
        try {
            text = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<MonoBankCurrencyResponseDto> responseDtos = convertResponse(text);
        return responseDtos.stream()
                .map(item -> {
                    RateResponseDto dto = new RateResponseDto();
                    dto.setCurrencyTo(item.getCurrencyA());
                    dto.setCurrencyFrom(item.getCurrencyB());
                    dto.setRateBuy(CurrencyRateApiService.RoundToDecimalPlaces(item.getRateBuy(),3));
                    dto.setRateSell(CurrencyRateApiService.RoundToDecimalPlaces(item.getRateSell(),3));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private List<MonoBankCurrencyResponseDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, MonoBankCurrencyResponseDto.class)
                .getType();

        Map<Integer, Currency> currs = Map.of(
                980, Currency.UAH,
                840, Currency.USD,
                978, Currency.EUR
        );

        List<MonoBankCurrencyResponseDto> responseDtos = gson.fromJson(response, type);
        return  responseDtos.stream()
                .peek(item -> {
                    if (currs.containsKey(item.getCurrencyCodeA())) {
                        item.setCurrencyA(currs.get(item.getCurrencyCodeA()));
                    }
                    if (currs.containsKey(item.getCurrencyCodeB())) {
                        item.setCurrencyB(currs.get(item.getCurrencyCodeB()));
                    }
                })
                //.filter(item -> !Currency.RUR.equals(item.getCurrencyCodeA()))
                .filter(item -> item.getCurrencyA() != null)
                .filter(item -> item.getCurrencyB() == Currency.UAH)
                .collect(Collectors.toList());
    }

}

