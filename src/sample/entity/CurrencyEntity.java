package sample.entity;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The main entity contains some information provided by API
 *
 * <p>
 * It contains base currency, date of last update, and current rates.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
    private long timestamp;
    private String date;
    private String base;
    private Map<String, Double> rates;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
