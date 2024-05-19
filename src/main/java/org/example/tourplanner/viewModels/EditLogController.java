package org.example.tourplanner.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tourplanner.models.LogModel;

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
        editDateTimeTextField.setText(logModel.getDateTime().get());
        editCommentTextField.setText(logModel.getComment().get());
        editDifficultyTextField.setText(String.valueOf(logModel.getDifficulty().get()));
        editTotalDistanceTextField.setText(String.valueOf(logModel.getTotalDistance().get()));
        editTotalTimeTextField.setText(logModel.getTotalTime().get());
        editRatingChoiceBox.setValue(logModel.getRating().get());
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
            logModel.getDateTime().set(dateTime);
            logModel.getComment().set(comment);
            logModel.getDifficulty().set(difficulty);
            logModel.getTotalDistance().set(totalDistance);
            logModel.getTotalTime().set(totalTime);
            logModel.getRating().set(rating);

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