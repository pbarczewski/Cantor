package sample.decimalConfig;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class implements DecimalConfig interface. It is used to format number
 *
 */
public class DecimalConfigImpl implements DecimalConfig {

    /**
     * The method formats number to particular Big decimal value
     *
     * <p>
     * It accepts number value and return formatted specific BigDecimal, scale to 2 positions after comma
     * </p>
     *
     * @param value  the value
     * Returns formatted value
     */
    @Override
    public BigDecimal configuration(Number value) {
        return new BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_UP);
    }
}
