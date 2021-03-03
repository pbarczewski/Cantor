package sample.repository;

import java.math.BigDecimal;

/**
 * Interface contains one method findCurrency that searches currency in CurrencyEntity
 *
 * @param <T>  the currency parameter
 */
public interface CurrencyFinder<T> {
    /**
     * The method searching target currency selected by user
     *
     * @param currency  the currency parameter
     * Returns currency
     */
    BigDecimal findCurrency(T currency);
}
