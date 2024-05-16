package org.example.tourplanner.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tourplanner.Models.TourModel;

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

    private MainViewModel viewModel;
    private TourModel tourModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
        editTransportType.getItems().setAll("Car", "Bike", "Walk");
    }

    public void setTourModel(TourModel tourModel) {
        this.tourModel = tourModel;

        // Setze die Werte der ausgewählten Tour in die Textfelder und ChoiceBox
        editNameTextField.setText(tourModel.getName().get());
        editDescriptionTextField.setText(tourModel.getTourDescription().get());
        editFromTextField.setText(tourModel.getFrom().get());
        editToTextField.setText(tourModel.getTo().get());
        editTimeTextField.setText(tourModel.getTime().get());
        editDistanceTextField.setText(String.valueOf(tourModel.getDistance().get()));
        editTransportType.setValue(tourModel.getTransportType().get());
        editRouteInformationTextField.setText(tourModel.getRouteInformation().get());
    }

    @FXML
    private void editTour() {
        if (tourModel != null) {
            String oldName = tourModel.getName().get();

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
            tourModel.getName().set(name);
            tourModel.getTourDescription().set(description);
            tourModel.getFrom().set(from);
            tourModel.getTo().set(to);
            tourModel.getTime().set(time);
            tourModel.getDistance().set(distance);
            tourModel.getTransportType().set(transportType);
            tourModel.getRouteInformation().set(routeInformation);

            viewModel.getTourNames().remove(oldName);
            viewModel.getTourNames().add(editNameTextField.getText());

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