package BankUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import currencyBot.RateResponseDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ChooseCurr {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private static final String urlPrivat = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    private static final String urlNBU = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final String urlMono = "https://api.monobank.ua/bank/currency";
    private static final String template = "Currency replCur, rate buy: replRateBay; rate sell: replRateSell;";
    private static final Gson gson = new Gson();

    static Type typePrivat = new TypeToken<List<PrivatBankCurrencyResponseDto>>() {
    }.getType();
    static Type typeNBU = new TypeToken<List<NbuCurrencyResponseDto>>() {
    }.getType();
    static Type typeMono = new TypeToken<List<MonoBankCurrencyResponseDto>>() {
    }.getType();

    public static <T> List<T> sendGetBank(URI uri,Type typeBank) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), typeBank);
    }

    public static RateResponseDto getPrivatAPI() throws IOException, InterruptedException {
        final List<PrivatBankCurrencyResponseDto> datePrivat = sendGetBank(URI.create(urlPrivat),typePrivat);
        return getPrivat(datePrivat);
    }
    public static RateResponseDto getMonoAPI() throws IOException, InterruptedException {
        final List<MonoBankCurrencyResponseDto> dateMono = sendGetBank(URI.create(urlMono),typeMono);
        return getMonobank(dateMono);
    }

    public static RateResponseDto getNBUAPI() throws IOException, InterruptedException {
        final List<NbuCurrencyResponseDto> dateNBU = sendGetBank(URI.create(urlNBU),typeNBU);
        return getNbu(dateNBU);
    }

    public static RateResponseDto getPrivat(List<PrivatBankCurrencyResponseDto> datePr) {
        RateResponseDto bank = new RateResponseDto();
        for (PrivatBankCurrencyResponseDto currency : datePr) {
            switch (currency.getCcy()) {
                case "USD":
                    datePr.stream()
                            .replaceAll("replCur", item.getCurrencyTo().toString())
                            .replaceAll("replRateBay", item.getRateBuy().toString())
                            .replaceAll("replRateSell", item.getRateSell().toString()))
                    break;
                case "EUR":

                    break;
            }
        }
        return bank;
    }
    public static RateResponseDto getMonobank(List<MonoBankCurrencyResponseDto> dateM) {
        RateResponseDto bank = new RateResponseDto();
        for (MonoBankCurrencyResponseDto currency : dateM) {
            switch (currency.getCurrencyCodeA()) {
                case "USD":
                    dateM.stream()
                            .replaceAll("replCur", item.getCurrencyTo().toString())
                            .replaceAll("replRateBay", item.getRateBuy().toString())
                            .replaceAll("replRateSell", item.getRateSell().toString()))
                    break;
                case "EUR":

                    break;
            }
        }
        return bank;
    }
    public static RateResponseDto getNbu(List<NbuCurrencyResponseDto> dateN) {
        RateResponseDto bank = new RateResponseDto();
        for (NbuCurrencyResponseDto currency : dateN) {
            switch (currency.getCc()) {
                case "USD":
                    dateN.stream()
                            .map(item -> template
                                    .replaceAll("replCur", item.getCc().toString())
                                    .replaceAll("replRateBay", item.getRate().toString());
                    break;
                case "EUR":

                    break;
            }
        }
        return bank;
    }

}
