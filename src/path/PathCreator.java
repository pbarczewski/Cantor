package path;

/**
 * Interface for creating path where report has to be placed
 * <p>
 * It contains one method createPath that accepts two parameters
 * </p>
 */
public interface PathCreator {
    /**
     * The method building path
     *
     * @param path  the path parameter
     * @param reportName  the reportName parameter
     * Returns String contains path
     */
    String createPath(String path, String reportName);
}
