package path;

import java.io.File;

/**
 * Implementation of PathCreator interface
 *
 */
public class PathCreatorImpl implements PathCreator {
    private String completedPath;

    /**
     * Creates final path in which report is going to be placed
     * <p>
     * The method accepts two parameters, both are String.
     * It checks whether report name matches to regex.
     * The name of report should ends with ".txt"
     * </p>
     *
     * @param path  the path parameter
     * @param reportName  the reportName parameter
     * Returns the final path with report name
     */
    @Override
    public String createPath(String path, String reportName) {
        StringBuilder reportPath = new StringBuilder();
        reportPath.append(path);
        reportPath.append(File.separator);
        reportPath.append(reportName);
        reportPath.append(".txt");
        completedPath = reportPath.toString();
        if(completedPath.matches(".*[^\\.]\\.txt$")) {
            return completedPath;
        }
       return null;
    }
}
