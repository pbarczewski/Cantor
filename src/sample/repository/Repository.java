package sample.repository;

import java.io.IOException;
import java.util.Map;

/**
 * Interface that connect with URL for getting information from API
 *
 */
public interface Repository {
    /**
     * The method accepts url parameter for getting data from API
     *
     * @param url  the url parameter
     * Returns a map of data received from url
     * @throws IOException
     */
    Map<String, Double> getData(String url) throws IOException;
}
