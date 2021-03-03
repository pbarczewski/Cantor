package sample.reports;

import javafx.scene.control.Alert;
import sample.repository.RepositoryImpl;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class create report
 *
 * <p>
 * The class contains various methods necessary for creating completed report in the particular place.
 * </p>
 */
public class ReportCreator {
    private File report = null;
    private BufferedWriter bufferedWriter;
    private RepositoryImpl repository;

    /**
     * The method writes content to a previously created file
     *
     * <p>
     * It contains of private methods that are part of this class:
     * <li>createDate</li>
     * <li>showBasedCurrency</li>
     * <li>showTargetCurrency</li>
     * <li>showAmount</li>
     * <li>showResult</li>
     * </p>
     *
     * @param targetCurrency  the targetCurrency parameter
     * @param amount  the amount parameter
     * @param result  the result parameter
     * @throws IOException
     */
    public void createReport(String targetCurrency, String amount, String result) throws IOException {
        try {
            System.out.println(report);
            this.bufferedWriter = new BufferedWriter(new FileWriter(report.getAbsolutePath(), true));
            createDate();
            showBasedCurrency();
            showTargetCurrency(targetCurrency);
            showAmount(amount);
            showResult(result);
            this.bufferedWriter.write("=========== \n");
        } catch (IOException e) {

        } finally {
            if(bufferedWriter != null) {
                this.bufferedWriter.close();
            }
        }
    }

    /**
     * The method checks whether the directory selected by user exists.
     * If not it creates such directory with subfolders
     *
     * @param file  the file parameter
     */
    public void createDirectories(File file) {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * This method creates report in the path provided by user
     *
     * <p>
     * The method runs createDirectories method to check if directory provided by user exists.
     * If report doesn't exist the method creates it and shows alert with information about report name and path that has been placed
     * If report already exists the method shows alert with information that content of already created report has been updated
     * </p>
     * @param filePath  the filePath parameter
     * Returns the report
     * @throws IOException
     */
    public File createFile(String filePath) throws IOException {
            this.report = new File(filePath);
            createDirectories(report);
            if(!report.exists()) {
                report.createNewFile();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(null);
                alert.setHeaderText("Report has been created");
                alert.setContentText(String.format("'%s' has been created.\nThe report has been placed in the path below: %s", report.getName(), report.getAbsolutePath()));
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(String.format("'%s' has been updated", report.getName()));
                alert.setContentText(null);
                alert.show();
            }
            return report;
    }

    /**
     * The method shows information about date when report has been updated/created
     *
     * <p>
     * It provides a date in specific format "day-month-year hour:minutes:seconds"
     * </p>
     * @throws IOException
     */
    private void createDate() throws IOException {
        this.bufferedWriter.write(String.format("Created: %s \n", new SimpleDateFormat("dd-MM-YYYY HH:MM:ss").format(new Date())));
    }

    /**
     * The method shows based currency
     *
     * @throws IOException
     */
    private void showBasedCurrency() throws IOException {
        repository = new RepositoryImpl();
        repository.getData("EUR");
        this.bufferedWriter.write(String.format("Based currency: %s \n", repository.getCurrencyEntity().getBase()));
    }

    /**
     * The method shows target currency
     *
     * @param targetCurrency  the targetCurrency parameter
     * @throws IOException
     */
    private void showTargetCurrency(String targetCurrency) throws IOException {
        this.bufferedWriter.write(String.format("Target currency: %s \n", targetCurrency));
    }

    /**
     * The method shows amount of money that user wants to exchange
     *
     * @param amount  the amount parameter
     * @throws IOException
     */
    private void showAmount(String amount) throws IOException {
        this.bufferedWriter.write(String.format("Amount: %s \n", amount));
    }

    /**
     * The method shows final result of money that user will receive after exchange
     *
     * @param result  the result parameter
     * @throws IOException
     */
    private void showResult(String result) throws IOException {
        this.bufferedWriter.write(String.format("Result: %s \n", result));
    }

    public File getReport() {
        return report;
    }

    public void setReport(File report) {
        this.report = report;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public RepositoryImpl getRepository() {
        return repository;
    }

    public void setRepository(RepositoryImpl repository) {
        this.repository = repository;
    }
}
