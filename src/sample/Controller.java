package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import path.PathCreator;
import path.PathCreatorImpl;
import path.Paths;
import sample.exchanger.CurrencyExchangerImpl;
import sample.reports.ReportCreator;
import sample.repository.RepositoryImpl;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;


public class Controller {
    private RepositoryImpl repository = new RepositoryImpl();
    private ObservableList<String> availableChoices;
    private CurrencyExchangerImpl currencyExchanger;
    private static String reportName = Paths.DEFAULT_REPORT_NAME.getReportName();
    private static String path = Paths.DEFAULT_USER_PATH.getPath();
    @FXML
    private Label lastUpdate;
    @FXML
    private TextField targetCurrency;
    @FXML
    private TextField amount;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    private Label result;
    @FXML
    private Label sourceCurrency;

    public void initialize() {
        repository = new RepositoryImpl();
        currencyExchanger = new CurrencyExchangerImpl();
        amount.setText("1");
        targetCurrency.setEditable(false);
       try {
            Map<String, Double> map = repository.getData(sourceCurrency.getText());
            availableChoices = FXCollections.observableArrayList(repository.getCodes(map));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       lastUpdate.setText(repository.getDate());
       choiceBox.setItems(availableChoices);
    }

    @FXML
    private void choseTargetCurrency() {
        BigDecimal value = repository.findCurrency(choiceBox.getSelectionModel().getSelectedItem());
        targetCurrency.setText(value.toString());
    }

    @FXML
    private void calculate() {
        if (targetCurrency.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong value");
            alert.setHeaderText(null);
            alert.setContentText("Please select currency");
            alert.show();
        }
        else if (amount.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Amount can't be empty");
            alert.setHeaderText(null);
            alert.setContentText("Field can't be empty or less than 1");
            alert.show();
        }
        else {
            Integer amount = Integer.valueOf(this.amount.getText());
            BigDecimal value = new BigDecimal(targetCurrency.getText());
            result.setText(currencyExchanger.exchangeCurrency(value, amount).toString());
        }
    }

    @FXML
    private void generateReport() throws IOException {
        PathCreator pathCreator = new PathCreatorImpl();
        ReportCreator reportCreator = new ReportCreator();
        reportCreator.createFile(pathCreator.createPath(path, reportName));
        reportCreator.createReport(targetCurrency.getText(), amount.getText(), result.getText());
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    @FXML
    public void openMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("optionsWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Report");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openFolder() throws IOException {
        File reportPath = new File(path);
        if(reportPath.exists()) {
            Desktop.getDesktop().open(new File(path));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText("Folder " + reportPath.getAbsolutePath() + " doesn't exist");
            alert.show();
        }
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public RepositoryImpl getRepository() {
        return repository;
    }

    public void setRepository(RepositoryImpl repository) {
        this.repository = repository;
    }

    public ObservableList<String> getAvailableChoices() {
        return availableChoices;
    }

    public void setAvailableChoices(ObservableList<String> availableChoices) {
        this.availableChoices = availableChoices;
    }

    public CurrencyExchangerImpl getCurrencyExchanger() {
        return currencyExchanger;
    }

    public void setCurrencyExchanger(CurrencyExchangerImpl currencyExchanger) {
        this.currencyExchanger = currencyExchanger;
    }

    public static String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Label getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Label lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public TextField getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(TextField targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public TextField getAmount() {
        return amount;
    }

    public void setAmount(TextField amount) {
        this.amount = amount;
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ChoiceBox<String> choiceBox) {
        this.choiceBox = choiceBox;
    }

    public Label getResult() {
        return result;
    }

    public void setResult(Label result) {
        this.result = result;
    }

    public Label getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Label sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }
}
