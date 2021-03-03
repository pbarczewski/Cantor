package sample.repository;

import java.util.List;
import java.util.Map;

/**
 * Interface that list all codes that are listed in CurrencyEntity
 *
 */
public interface GetListofCurrenciesCode {
    /**
     * The method lists currency codes received from API
     *
     * @param jsonData  the jsonData parameter
     * Returns list of currency codes
     * @throws Exception
     */
    List<String> getCodes(Map<String, Double> jsonData) throws Exception;
}
