package org.example.tourplanner.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tourplanner.models.TourModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {

    @FXML
    private Button editButton;

    @FXML
    private TextField editDescriptionTextField;

    @FXML
    private TextField editDistanceTextField;

    @FXML
    private TextField editFromTextField;

    @FXML
    private TextField editNameTextField;

    @FXML
    private TextField editRouteInformationTextField;

    @FXML
    private TextField editTimeTextField;

    @FXML
    private TextField editToTextField;

    @FXML
    private ChoiceBox<String> editTransportType;

    private MainController viewModel;
    private TourModel tourModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainController.getInstance();
        editTransportType.getItems().setAll("Car", "Bike", "Walk");
    }

    public void setTourModel(TourModel tourModel) {
        this.tourModel = tourModel;

        // Setze die Werte der ausgewählten Tour in die Textfelder und ChoiceBox
        editNameTextField.setText(tourModel.getNameProperty().get());
        editDescriptionTextField.setText(tourModel.getTourDescriptionProperty().get());
        editFromTextField.setText(tourModel.getFromProperty().get());
        editToTextField.setText(tourModel.getToProperty().get());
        editTimeTextField.setText(tourModel.getTimeProperty().get());
        editDistanceTextField.setText(String.valueOf(tourModel.getDistanceProperty().get()));
        editTransportType.setValue(tourModel.getTransportTypeProperty().get());
        editRouteInformationTextField.setText(tourModel.getRouteInformationProperty().get());
    }

    @FXML
    private void editTour() {
        if (tourModel != null) {
            String oldName = tourModel.getNameProperty().get();

            String name = editNameTextField.getText();
            String description = editDescriptionTextField.getText();
            String from = editFromTextField.getText();
            String to = editToTextField.getText();
            String time = editTimeTextField.getText();
            String distanceText = editDistanceTextField.getText();
            String transportType = editTransportType.getValue();
            String routeInformation = editRouteInformationTextField.getText();

            if (name.isEmpty() || distanceText.isEmpty()) {
                showAlert("Name and Distance are required.");
                return;
            }

            float distance;
            try {
                distance = Float.parseFloat(distanceText);
            } catch (NumberFormatException e) {
                showAlert("Invalid distance input.");
                return;
            }

            // Aktualisiere die Werte der ausgewählten Tour
            tourModel.getNameProperty().set(name);
            tourModel.getTourDescriptionProperty().set(description);
            tourModel.getFromProperty().set(from);
            tourModel.getToProperty().set(to);
            tourModel.getTimeProperty().set(time);
            tourModel.getDistanceProperty().set(distance);
            tourModel.getTransportTypeProperty().set(transportType);
            tourModel.getRouteInformationProperty().set(routeInformation);

            viewModel.getTourNames().remove(oldName);
            viewModel.getTourNames().add(editNameTextField.getText());
            viewModel.updateSelectedTour(tourModel);

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
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();
    }
}