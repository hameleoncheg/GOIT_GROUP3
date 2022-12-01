package currencyBot;

import java.util.List;
import java.util.stream.Collectors;

public class PrettyResponseConverter {
    private static final String BUY = "buy";
    private static final String SELL = "sell";
    private static final String ERROR_MESSAGE = "Unknown command, write BUY or SELL";
    private static final String template = "Currency replCur, rate replRate;";
    public String prepareResponse(String command, List<RateResponseDto> dtos) {

        String response = "";
        if(BUY.equalsIgnoreCase(command)) {
            response = dtos.stream()
                    .map(item -> template
                            .replaceAll("replCur", item.getCurrencyTo().toString())
                            .replaceAll("replRate", item.getRateBuy().toString()))
                    .collect(Collectors.joining("\n"));
        }else if (SELL.equalsIgnoreCase(command)){
            response = dtos.stream()
                    .map(item -> template
                            .replaceAll("replCur", item.getCurrencyTo().toString())
                            .replaceAll("replRate", item.getRateSell().toString()))
                    .collect(Collectors.joining("\n"));
        }else {
            response = ERROR_MESSAGE;

        }
        return response;
    }
}

