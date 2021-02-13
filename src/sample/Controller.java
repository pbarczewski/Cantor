package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.exchanger.CurrencyExchangerImpl;
import sample.repository.RepositoryImpl;
import java.math.BigDecimal;
import java.util.Map;

//test

public class Controller {
    private RepositoryImpl repository;
    private ObservableList<String> availableChoices;
    private CurrencyExchangerImpl currencyExchanger;

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
    private void test() {
        BigDecimal value = repository.findCurrency(choiceBox.getSelectionModel().getSelectedItem());
        targetCurrency.setText(value.toString());
    }

    @FXML
    private void test2() {
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
}
