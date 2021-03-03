package sample.decimalConfig;

import java.math.BigDecimal;

/**
 * The interface that configures a format of BigDecimal
 *
 * @param <T>  the type of number
 */
public interface DecimalConfig<T extends Number> {
    BigDecimal configuration(T value);
}
