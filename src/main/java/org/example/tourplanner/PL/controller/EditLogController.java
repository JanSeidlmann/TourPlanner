package org.example.tourplanner.PL.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tourplanner.BL.models.LogModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditLogController implements Initializable {

    @FXML
    private Button editLogButton;

    @FXML
    private TextField editDateTimeTextField;

    @FXML
    private TextField editCommentTextField;

    @FXML
    private TextField editDifficultyTextField;

    @FXML
    private TextField editTotalDistanceTextField;

    @FXML
    private TextField editTotalTimeTextField;

    @FXML
    private ChoiceBox<Integer> editRatingChoiceBox;

    private MainController viewModel;
    private LogModel logModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainController.getInstance();
        editRatingChoiceBox.getItems().setAll(1, 2, 3, 4, 5);
    }

    public void setLogModel(LogModel logModel) {
        this.logModel = logModel;

        // Set the values of the selected log into the text fields and choice box
        editDateTimeTextField.setText(logModel.getDateTimeProperty().get());
        editCommentTextField.setText(logModel.getCommentProperty().get());
        editDifficultyTextField.setText(String.valueOf(logModel.getDifficultyProperty().get()));
        editTotalDistanceTextField.setText(String.valueOf(logModel.getTotalDistanceProperty().get()));
        editTotalTimeTextField.setText(logModel.getTotalTimeProperty().get());
        editRatingChoiceBox.setValue(logModel.getRatingProperty().get());
    }

    @FXML
    private void editLog() {
        if (logModel != null) {
            String dateTime = editDateTimeTextField.getText();
            String comment = editCommentTextField.getText();
            String difficultyText = editDifficultyTextField.getText();
            String totalDistanceText = editTotalDistanceTextField.getText();
            String totalTime = editTotalTimeTextField.getText();
            Integer rating = editRatingChoiceBox.getValue();

            if (dateTime.isEmpty() || difficultyText.isEmpty() || totalDistanceText.isEmpty() || rating == null) {
                showAlert("Date/Time, Difficulty, Distance, and Rating are required.");
                return;
            }

            int difficulty;
            float totalDistance;
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

            // Update the values of the selected log
            logModel.getDateTimeProperty().set(dateTime);
            logModel.getCommentProperty().set(comment);
            logModel.getDifficultyProperty().set(difficulty);
            logModel.getTotalDistanceProperty().set(totalDistance);
            logModel.getTotalTimeProperty().set(totalTime);
            logModel.getRatingProperty().set(rating);

            closeStage();
        }
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
        Stage stage = (Stage) editLogButton.getScene().getWindow();
        stage.close();
    }
}
