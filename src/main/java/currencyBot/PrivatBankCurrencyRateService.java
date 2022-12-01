package currencyBot;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class PrivatBankCurrencyRateService implements CurrencyRateApiService {
    String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    private Gson gson = new Gson();

    public List<RateResponseDto> getRates(){
        String text = null;
        try {
            text = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<PrivatBankCurrencyResponseDto> responseDtos = convertResponse(text);
        return responseDtos.stream()
                .map(item -> {
                    RateResponseDto dto = new RateResponseDto();
                    dto.setCurrencyTo(item.getCcy());
                    dto.setCurrencyFrom(item.getBase_ccy());
                    dto.setRateBuy(item.getBuy());
                    dto.setRateSell(item.getSale());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private List<PrivatBankCurrencyResponseDto> convertResponse(String response) {
        Type type = TypeToken
                .getParameterized(List.class, PrivatBankCurrencyResponseDto.class)
                .getType();
        List<PrivatBankCurrencyResponseDto> responseDtos = gson.fromJson(response, type);
        return  responseDtos.stream()
                .filter(item -> !Currency.RUR.equals(item.getCcy()))
                .collect(Collectors.toList());
    }

}

