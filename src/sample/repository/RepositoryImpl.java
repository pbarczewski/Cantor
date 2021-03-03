package sample.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import sample.decimalConfig.DecimalConfigImpl;
import sample.entity.CurrencyEntity;
import sample.updateDate.UpdateDateImpl;
import sample.url.Url;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;


/**
 * The class implemented interfaces Repository, CurrencyFinder, GetListofCurrenciesCode
 *
 * <p>
 * It connects with url address for receiving latest data
 * </p>
 */
public class RepositoryImpl implements Repository, CurrencyFinder, GetListofCurrenciesCode {
    private Map<String, Double> currenciesData = new HashMap<>();
    private final DecimalConfigImpl decimalConfig = new DecimalConfigImpl();
    private final UpdateDateImpl updateDate = new UpdateDateImpl();
    private CurrencyEntity currencyEntity;

    /**
     * The method accepts basedCurrency that is a basic currency
     *
     * <p>
     * It connects with url address and saved received data into a map.
     * If url address is wrong it throws IllegalArgumentException
     * </p>
     *
     * @param basedCurrency  the basedCurrency parameter
     * Returns data of currencies
     * @throws IOException
     */
    @Override
    public Map<String, Double> getData(String basedCurrency) throws IOException {
        if (basedCurrency.matches("[A-Z]{3}")) {
            StringBuilder url = new StringBuilder();
            url.append(Url.DATA_URL.getUrl());
            url.append(basedCurrency);
            currencyEntity = new ObjectMapper().treeToValue(new ObjectMapper().readTree(new URL(url.toString())), CurrencyEntity.class);
            currenciesData = currencyEntity.getRates();
            return currenciesData;
        } else {
            throw new IllegalArgumentException("Url address is wrong");
        }
    }

    /**
     * The method searching specific currency and returns selected value
     *
     * <p>
     * It accepts an object and throws NullPointerException if nothing is present
     * </p>
     *
     * @param currency  the currency parameter
     * Returns selected currency
     */
    @Override
    public BigDecimal findCurrency(Object currency) {
        Optional<Double> searchedValue = this.currenciesData.entrySet()
                .stream()
                .filter(data -> data.getKey().equals(currency.toString()))
                .map(Map.Entry::getValue)
                .findFirst();
        if(searchedValue.isPresent()) {
            return decimalConfig.configuration(searchedValue.get());
        } else {
            throw new NullPointerException("Currency hasn't been found");
        }
    }

    /**
     * The method searches for currency codes
     *
     * <p>
     * The method accepts jsonData
     * It throws an Exception if data isn't found
     * </p>
     *
     * @param jsonData  the jsonData parameter
     * Returns list of currency codes
     * @throws Exception
     */
    @Override
    public List<String> getCodes(Map<String, Double> jsonData) throws Exception {
        if(jsonData.size() == 0) {
            throw new Exception("Data wasn't read correctly");
        }
        return new ArrayList<>(currenciesData.keySet());
    }

    public String getDate() {
        return updateDate.lastUpdate(currencyEntity.getTimestamp());
    }

    public Map<String, Double> getCurrenciesData() {
        return currenciesData;
    }

    public void setCurrenciesData(Map<String, Double> currenciesData) {
        this.currenciesData = currenciesData;
    }

    public DecimalConfigImpl getDecimalConfig() {
        return decimalConfig;
    }

    public UpdateDateImpl getUpdateDate() {
        return updateDate;
    }

    public CurrencyEntity getCurrencyEntity() {
        return currencyEntity;
    }

    public void setCurrencyEntity(CurrencyEntity currencyEntity) {
        this.currencyEntity = currencyEntity;
    }
}
