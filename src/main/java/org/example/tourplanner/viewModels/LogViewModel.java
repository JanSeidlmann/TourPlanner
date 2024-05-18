package org.example.tourplanner.viewModels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class LogViewModel implements Initializable {

    @FXML
    public ListView<String> logList;

    @FXML
    private Label selectedTour;
    @FXML
    private Button editLogBnt;
    @FXML
    private Button deleteLogBnt;
    @FXML
    private Button addLogBnt;

    private MainViewModel viewModel;

    @FXML
    private void deleteLog(ActionEvent actionEvent) {
    }
    @FXML
    private void editLog(ActionEvent actionEvent) {
    }
    @FXML
    private void addLog(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
        logList.setItems(viewModel.getLogNames());
    }


}
