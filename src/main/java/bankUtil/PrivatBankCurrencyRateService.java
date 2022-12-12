package bankUtil;


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

public class PrivatBankCurrencyRateService implements CurrencyRateApiService {

    String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    private Gson gson = new Gson();

    @Override
    public List<RateResponseDto> getRates(List<Currency> curr, int numberAfterComma) throws IOException {
        String text = null;
            text = Jsoup.connect(url).ignoreContentType(true).get().body().text();

        List<PrivatBankCurrencyResponseDto> responseDtos = convertResponse(text, curr);
        return responseDtos.stream()
                .map(item -> {
                    RateResponseDto dto = new RateResponseDto();
                    dto.setCurrencyTo(item.getCcy());
                    dto.setCurrencyFrom(item.getBase_ccy());
                    dto.setRateBuy(CurrencyRateApiService.RoundToDecimalPlaces(item.getBuy(),numberAfterComma));
                    dto.setRateSell(CurrencyRateApiService.RoundToDecimalPlaces(item.getSale(),numberAfterComma));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private List<PrivatBankCurrencyResponseDto> convertResponse(String response,  List<Currency> curr) {
        Type type = TypeToken
                .getParameterized(List.class, PrivatBankCurrencyResponseDto.class)
                .getType();
        List<PrivatBankCurrencyResponseDto> responseDtos = gson.fromJson(response, type);
        return  responseDtos.stream()
                .filter(item -> curr.contains(item.getCcy()))
                .collect(Collectors.toList());
    }

}

