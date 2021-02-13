package sample.decimalConfig;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalConfigImpl implements DecimalConfig {
    @Override
    public BigDecimal configuration(Number value) {
        return new BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_UP);
    }
}
