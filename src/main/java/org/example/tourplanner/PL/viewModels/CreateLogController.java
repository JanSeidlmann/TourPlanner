package org.example.tourplanner.PL.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.DefaultInjector;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateLogController implements Initializable {

    @FXML
    private Button createLogButton;

    @FXML
    private TextField dateTimeTextField;

    @FXML
    private TextField commentTextField;

    @FXML
    private TextField difficultyTextField;

    @FXML
    private TextField totalDistanceTextField;

    @FXML
    private TextField totalTimeTextField;

    @FXML
    private ChoiceBox<Integer> ratingChoiceBox;

    private final CreateLogViewModel createLogViewModel;

    private MainController mainController;

    public CreateLogController() {
        this.createLogViewModel = DefaultInjector.getService(CreateLogViewModel.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();
        ratingChoiceBox.getItems().setAll(1, 2, 3, 4, 5);

        LogModel newLog = new LogModel();
        bindProperties(newLog);
    }

    private void bindProperties(LogModel log) {
        dateTimeTextField.textProperty().bindBidirectional(log.getDateTimeProperty());
        commentTextField.textProperty().bindBidirectional(log.getCommentProperty());
        difficultyTextField.textProperty().bindBidirectional(log.getDifficultyProperty(), new NumberStringConverter());
        totalDistanceTextField.textProperty().bindBidirectional(log.getTotalDistanceProperty(), new NumberStringConverter());
        totalTimeTextField.textProperty().bindBidirectional(log.getTotalTimeProperty());
        ratingChoiceBox.valueProperty().bindBidirectional(log.getRatingProperty().asObject());
    }

    @FXML
    private void onCreateLogButtonClicked() {
        String dateTime;
        String difficultyText;
        String totalDistanceText;
        String rating;

        if (dateTimeTextField.getText() == null || dateTimeTextField.getText().isEmpty() ||
                difficultyTextField.getText().isEmpty() || totalDistanceTextField.getText().isEmpty() ||
                ratingChoiceBox.getValue() == null) {
            showAlert("Date/Time, Difficulty, Distance, and Rating are required.");
            return;
        } else {
            dateTime = dateTimeTextField.getText();
            difficultyText = difficultyTextField.getText();
            totalDistanceText = totalDistanceTextField.getText();
            rating = ratingChoiceBox.getValue().toString();
        }

        int difficulty;
        float totalDistance;
        int logRating;

        try {
            difficulty = Integer.parseInt(difficultyText);
        } catch (NumberFormatException e) {
            showAlert("Invalid difficulty input.");
            return;
        }

        try {
            totalDistance = Float.parseFloat(totalDistanceText);
        } catch (NumberFormatException e) {
            showAlert("Invalid distance input.");
            return;
        }

        try {
            logRating = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            showAlert("Invalid rating input.");
            return;
        }

        LogModel log = new LogModel(
                dateTime,
                commentTextField.getText(),
                difficulty,
                totalDistance,
                totalTimeTextField.getText(),
                logRating,
                mainController.getSelectedTour()
        );
        createLogViewModel.addLog(log);
        mainController.switchToLogsTab();
        closeStage();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onCancelButtonClicked() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) createLogButton.getScene().getWindow();
        stage.close();
    }
}