package org.example.tourplanner.PL.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.PL.viewmodels.CreateTourViewModel;

import java.net.URL;
import java.util.ResourceBundle;

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

    private final CreateTourViewModel createTourViewModel;

    private MainController mainController;

    public CreateTourController() {
        this.createTourViewModel = DefaultInjector.getService(CreateTourViewModel.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();
        transportType.getItems().setAll("Car", "Bike", "Walk");

        TourModel newTour = new TourModel();
        bindProperties(newTour);
    }

    private void bindProperties(TourModel tour) {
        nameTextField.textProperty().bindBidirectional(tour.getNameProperty());
        descriptionTextField.textProperty().bindBidirectional(tour.getTourDescriptionProperty());
        fromTextField.textProperty().bindBidirectional(tour.getFromProperty());
        toTextField.textProperty().bindBidirectional(tour.getToProperty());
        transportType.valueProperty().bindBidirectional(tour.getTransportTypeProperty());
        distanceTextField.textProperty().bindBidirectional(tour.getDistanceProperty(), new NumberStringConverter());
        timeTextField.textProperty().bindBidirectional(tour.getTimeProperty());
        routeInformationTextField.textProperty().bindBidirectional(tour.getRouteInformationProperty());
    }

    @FXML
    private void onCreateButtonClicked() {
        if (nameTextField.getText() == null || nameTextField.getText().isEmpty() || distanceTextField.getText().isEmpty()) {
            showAlert("Name and Distance are required.");
            return;
        }
        if (routeInformationTextField.getText() == null) {
            routeInformationTextField.setText("map-placeholder.png");
        }

        TourModel tour = new TourModel(
                nameTextField.getText(),
                descriptionTextField.getText(),
                fromTextField.getText(),
                toTextField.getText(),
                transportType.getSelectionModel().getSelectedItem(),
                Float.parseFloat(distanceTextField.getText()),
                timeTextField.getText(),
                routeInformationTextField.getText()
        );

        mainController.addTourName(tour.getName());
        mainController.addTour(tour);
        createTourViewModel.addTour(tour);
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
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }
}
