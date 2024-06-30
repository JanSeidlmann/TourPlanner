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
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.TourPlannerApplication;
import org.example.tourplanner.BL.models.LogModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class LogController implements Initializable, Injectable {

    @FXML
    public TableView<LogModel> logTableView;

    @FXML
    public TableColumn<LogModel, String> dateTimeColumn;
    @FXML
    public TableColumn<LogModel, String> commentColumn;
    @FXML
    public TableColumn<LogModel, Integer> difficultyColumn;
    @FXML
    public TableColumn<LogModel, Float> totalDistanceColumn;
    @FXML
    public TableColumn<LogModel, String> totalTimeColumn;
    @FXML
    public TableColumn<LogModel, Integer> ratingColumn;

    @FXML
    public Label selectedTour;
    @FXML
    private Button editLogBnt;
    @FXML
    private Button deleteLogBnt;

    public MainController mainController;

    @FXML
    public void deleteLog(ActionEvent actionEvent) {
        LogModel selectedLog = logTableView.getSelectionModel().getSelectedItem();
        mainController.removeLog(selectedLog);
    }
    @FXML
    public void editLog(ActionEvent actionEvent) throws IOException {
        LogModel selectedLog = logTableView.getSelectionModel().getSelectedItem();
        if (selectedLog != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("/org/example/tourplanner/editLog.fxml"));
            Parent root = fxmlLoader.load();

            EditLogController editLogController = fxmlLoader.getController();
            editLogController.setLogModel(selectedLog);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Log");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } else {
            log.warn("No log selected for editing.");
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
