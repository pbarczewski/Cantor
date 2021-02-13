package sample.decimalConfig;

import java.math.BigDecimal;

public interface DecimalConfig<T extends Number> {
    BigDecimal configuration(T value);
}
