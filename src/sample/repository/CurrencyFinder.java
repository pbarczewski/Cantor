package sample.repository;

import java.math.BigDecimal;

public interface CurrencyFinder<T> {
    BigDecimal findCurrency(T currency);
}
