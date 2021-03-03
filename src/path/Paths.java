package path;

/**
 * This enum contains default names determining
 * where report will be placed and how it will be named
 *
 * <p>
 * The default name for variable DEFAULT_USER_PATH is user home catalog
 * The default name for variable DEFAULT_REPORT_NAME is 'Report'
 * </p>
 */
public enum Paths {
    DEFAULT_USER_PATH(System.getProperty("user.home")),
    DEFAULT_REPORT_NAME("Report");
    private String path;
    private String reportName;

    Paths(String path, String reportName) {
        this.path = path;
        this.reportName = reportName;
    }

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
