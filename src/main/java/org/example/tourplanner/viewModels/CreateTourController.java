package org.example.tourplanner.viewModels;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.example.tourplanner.models.TourModel;

import java.io.IOException;
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

    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
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
        String tourName = nameTextField.getText();
        float distance = Float.parseFloat(distanceTextField.getText());
        TourModel tour = new TourModel(
                nameTextField.getText(),
                descriptionTextField.getText(),
                fromTextField.getText(),
                toTextField.getText(),
                transportType.getSelectionModel().getSelectedItem(),
                distance,
                timeTextField.getText(),
                routeInformationTextField.getText()
        );
        viewModel.addTourName(tourName);
        viewModel.addTour(tour);
        closeStage();
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

