package org.example.tourplanner.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.example.tourplanner.models.TourModel;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.beans.binding.Bindings.isEmpty;

public class CreateTourController implements Initializable {

    @FXML
    private Button createButton;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField distanceTextField;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField routeInformationTextField;

    @FXML
    private TextField timeTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private ChoiceBox<String> transportType;

    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();
        transportType.getItems().setAll("Car", "Bike", "Walk");

        TourModel newTour = new TourModel();
        nameTextField.textProperty().bindBidirectional(newTour.getName());
        descriptionTextField.textProperty().bindBidirectional(newTour.getTourDescription());
        fromTextField.textProperty().bindBidirectional(newTour.getFrom());
        toTextField.textProperty().bindBidirectional(newTour.getTo());
        transportType.valueProperty().bindBidirectional(newTour.getTransportType());
        distanceTextField.textProperty().bindBidirectional(newTour.getDistance(), new NumberStringConverter());
        timeTextField.textProperty().bindBidirectional(newTour.getTime());
        routeInformationTextField.textProperty().bindBidirectional(newTour.getRouteInformation());
    }

    @FXML
    private void onCreateButtonClicked() {
        String tourName;
        String distanceText;

        if (nameTextField.getText() == null || nameTextField.getText().isEmpty() || distanceTextField.getText().isEmpty()) {
            showAlert("Name and Distance are required.");
            return;
        } else {
            tourName = nameTextField.getText();
            distanceText = distanceTextField.getText();
        }

        float distance;

        try {
            distance = Float.parseFloat(distanceText);
        } catch (NumberFormatException e) {
            showAlert("Invalid distance input.");
            return;
        }

        TourModel tour = new TourModel(
                tourName,
                descriptionTextField.getText(),
                fromTextField.getText(),
                toTextField.getText(),
                transportType.getSelectionModel().getSelectedItem(),
                distance,
                timeTextField.getText(),
                "/org/example/tourplanner/img/map-placeholder.png"
        );
        mainController.addTourName(tourName);
        mainController.addTour(tour);
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
        Stage stage = (Stage) createButton.getScene().getWindow();  // Hole die Stage über den Save-Button
        stage.close();  // Schließe die Stage
    }
}