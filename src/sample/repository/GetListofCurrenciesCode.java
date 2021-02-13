package sample.repository;

import java.util.List;
import java.util.Map;

public interface GetListofCurrenciesCode {
    List<String> getCodes(Map<String, Double> jsonData) throws Exception;
}
