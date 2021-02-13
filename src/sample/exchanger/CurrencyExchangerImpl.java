package sample.exchanger;

import javafx.scene.control.Alert;
import sample.decimalConfig.DecimalConfigImpl;

import java.math.BigDecimal;

public class CurrencyExchangerImpl implements CurrencyExchanger{
    @Override
    public BigDecimal exchangeCurrency(BigDecimal bigDecimal, Integer amount) {
        DecimalConfigImpl decimalConfig = new DecimalConfigImpl();
        BigDecimal test = decimalConfig.configuration(bigDecimal);
        if(bigDecimal.compareTo(BigDecimal.ZERO) <= 0 || amount < 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Amount is less than 1");
            alert.setHeaderText(null);
            alert.setContentText("Please set a correct value of amount (can't be less than 0");
            alert.show();
        }
        return BigDecimal.valueOf(amount).multiply(test);
    }
}
