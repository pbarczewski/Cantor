package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import path.Paths;

import java.io.File;
import java.io.IOException;

public class OptionsController {
    @FXML
    private TextField path;
    @FXML
    private TextField name;


    public void initialize(){
        path.setText(Paths.DEFAULT_USER_PATH.getPath());
        name.setText(Paths.DEFAULT_REPORT_NAME.getPath());
    }

    public void setReport(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        Controller mainController = loader.getController();
        mainController.setReportName(name.getText());
        mainController.setPath(path.getText());
        stage.close();
        close(event);
    }

    @FXML
    public void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =  directoryChooser.showDialog(null);
        directoryChooser.setTitle("Select path");
        path.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    public void close(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }
}
