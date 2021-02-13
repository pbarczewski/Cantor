package sample.exchanger;

import java.math.BigDecimal;

public interface CurrencyExchanger {
    BigDecimal exchangeCurrency(BigDecimal value, Integer amount);
}
