package currencyBot;

import java.util.List;
import java.util.stream.Collectors;

public class PrettyResponseConverter {

    private static final String ERROR_MESSAGE = "Unknown command, write BUY or SELL";
    private static final String template = "Курс replCur, купівлі: replRateBay; продажу: replRateSell;";

    public static String prepareResponse(List<RateResponseDto> dtos) {

        String response = "";

        response = dtos.stream()
                .map(item -> template
                        .replaceAll("replCur", item.getCurrencyTo().toString())
                        .replaceAll("replRateBay", item.getRateBuy().toString())
                        .replaceAll("replRateSell", item.getRateSell().toString()))
                .collect(Collectors.joining("\n"));
        if (response == "") {
            response = ERROR_MESSAGE;

        }
        return response;
    }
}

