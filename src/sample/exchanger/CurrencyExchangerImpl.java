package sample.exchanger;

import javafx.scene.control.Alert;
import sample.decimalConfig.DecimalConfigImpl;

import java.math.BigDecimal;

/**
 * The class implements CurrencyExchanger interface.
 *
 */
public class CurrencyExchangerImpl implements CurrencyExchanger {
    /**
     * The method is used to calculate the currency value after the exchange
     *
     * <p>
     * The method accepts two parameters, the value parameter can't be less or equal to zero.
     * Before result is calculated, method sets a correct format for value parameter
     * </p>
     *
     * @param value   the value parameter
     * @param amount  the amount parameter
     * Returns the result of multiplication by quantity and currency value
     */
    @Override
    public BigDecimal exchangeCurrency(BigDecimal value, Integer amount) {
        DecimalConfigImpl decimalConfig = new DecimalConfigImpl();
        BigDecimal formattedValue = decimalConfig.configuration(value);
        if(value.compareTo(BigDecimal.ZERO) <= 0 || amount < 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Amount is less than 1");
            alert.setHeaderText(null);
            alert.setContentText("Please set a correct value of amount (can't be less than 0");
            alert.show();
        }
        return BigDecimal.valueOf(amount).multiply(formattedValue);
    }
}
