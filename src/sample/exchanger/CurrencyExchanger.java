package sample.exchanger;

import java.math.BigDecimal;


/**
 * Interface that is used for counting result
 *
 */
public interface CurrencyExchanger {
    BigDecimal exchangeCurrency(BigDecimal value, Integer amount);
}
