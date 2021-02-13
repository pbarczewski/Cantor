package sample.repository;

import java.io.IOException;
import java.util.Map;

public interface Repository {
    Map<String, Double> getData(String url) throws IOException;
}
