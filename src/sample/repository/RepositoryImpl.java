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

public class RepositoryImpl implements Repository, CurrencyFinder, GetListofCurrenciesCode {
    private Map<String, Double> currenciesData = new HashMap<>();
    private final DecimalConfigImpl decimalConfig = new DecimalConfigImpl();
    private final UpdateDateImpl updateDate = new UpdateDateImpl();
    private CurrencyEntity currencyEntity;

    @Override
    public Map<String, Double> getData(String baseCurrency) throws IOException {
        if (baseCurrency.matches("[A-Z]{3}")) {
            StringBuilder url = new StringBuilder();
            url.append(Url.DATA_URL.getUrl());
            url.append(baseCurrency);
            currencyEntity = new ObjectMapper().treeToValue(new ObjectMapper().readTree(new URL(url.toString())), CurrencyEntity.class);
            currenciesData = currencyEntity.getRates();
            return currenciesData;
        } else {
            throw new IllegalArgumentException("Url address is wrong");
        }
    }

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
}
