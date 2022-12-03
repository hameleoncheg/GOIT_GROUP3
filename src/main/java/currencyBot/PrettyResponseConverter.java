package currencyBot;

import java.util.List;
import java.util.stream.Collectors;

public class PrettyResponseConverter {
    private static final String BUY = "buy";
    private static final String SELL = "sell";
    private static final String ERROR_MESSAGE = "Unknown command, write BUY or SELL";
    private static final String template = "Currency replCur, rate buy: replRateBay; rate sell: replRateSell;";

    public String prepareResponse(String command, List<RateResponseDto> dtos) {

        String response = "";

        response = dtos.stream()
                .map(item -> template
                        .replaceAll("replCur", item.getCurrencyTo().toString())
                        .replaceAll("replRateBay", item.getRateBuy().toString())
                        .replaceAll("replRateSell", item.getRateSell().toString()))
                .collect(Collectors.joining("\n"));
//        }else if (SELL.equalsIgnoreCase(command)){
//            response = dtos.stream()
//                    .map(item -> template
//                            .replaceAll("replCur", item.getCurrencyTo().toString())
//                            .replaceAll("replRate", item.getRateSell().toString()))
//                    .collect(Collectors.joining("\n"));
        if (response == "") {
            response = ERROR_MESSAGE;

        }
        return response;
    }
}

