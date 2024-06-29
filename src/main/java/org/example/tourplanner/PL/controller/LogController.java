package org.example.tourplanner.PL.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.TourPlannerApplication;
import org.example.tourplanner.BL.models.LogModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogController implements Initializable, Injectable {

    @FXML
    public TableView<LogModel> logTableView;

    @FXML
    private TableColumn<LogModel, String> dateTimeColumn;
    @FXML
    private TableColumn<LogModel, String> commentColumn;
    @FXML
    private TableColumn<LogModel, Integer> difficultyColumn;
    @FXML
    private TableColumn<LogModel, Float> totalDistanceColumn;
    @FXML
    private TableColumn<LogModel, String> totalTimeColumn;
    @FXML
    private TableColumn<LogModel, Integer> ratingColumn;

    @FXML
    private Label selectedTour;
    @FXML
    private Button editLogBnt;
    @FXML
    private Button deleteLogBnt;

    private MainController mainController;

    @FXML
    private void deleteLog(ActionEvent actionEvent) {
        LogModel selectedLog = logTableView.getSelectionModel().getSelectedItem();
        mainController.removeLog(selectedLog);
    }
    @FXML
    private void editLog(ActionEvent actionEvent) throws IOException {
        LogModel selectedLog = logTableView.getSelectionModel().getSelectedItem();
        if (selectedLog != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("/org/example/tourplanner/editLog.fxml"));
            Parent root = fxmlLoader.load();

            EditLogController editLogController = fxmlLoader.getController();
            editLogController.setLogModel(selectedLog);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Tour");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a log to edit.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();

        dateTimeColumn.setCellValueFactory(data -> data.getValue().getDateTimeProperty());
        commentColumn.setCellValueFactory(data -> data.getValue().getCommentProperty());
        difficultyColumn.setCellValueFactory(data -> data.getValue().getDifficultyProperty().asObject());
        totalDistanceColumn.setCellValueFactory(data -> data.getValue().getTotalDistanceProperty().asObject());
        totalTimeColumn.setCellValueFactory(data -> data.getValue().getTotalTimeProperty());
        ratingColumn.setCellValueFactory(data -> data.getValue().getRatingProperty().asObject());

        logTableView.setItems(mainController.getLogs());
        selectedTour.setText("Selected tour: " + mainController.getSelectedTourName());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLogsOfTour() {
        logTableView.setItems(mainController.getLogs());
        selectedTour.setText("Selected tour: " + mainController.getSelectedTourName());
    }
}
