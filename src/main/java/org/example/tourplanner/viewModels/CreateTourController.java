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
import org.example.tourplanner.models.TourModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTourController implements Initializable {

    @FXML
    private Button createButton;

    @FXML
    private TextField descriptionNameField;

    @FXML
    private TextField distanceNameField;

    @FXML
    private TextField fromNameField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField routeInformationNameField;

    @FXML
    private TextField timeNameField;

    @FXML
    private TextField toNameField;

    @FXML
    private ChoiceBox<String> transportType;

    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
        transportType.getItems().setAll("Car", "Bike", "Walk");

        TourModel newTour = new TourModel();
        nameTextField.textProperty().bindBidirectional(newTour.getName());
        transportType.valueProperty().bindBidirectional(newTour.getTransportType());

    }

    @FXML
    private void onCreateButtonClicked() {
        String tourName = nameTextField.getText();
        viewModel.addTourName(tourName);
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

/*    @FXML
    private void removeTour() {
        ObservableList<TourModel> selectedItems = tourPlannerController.tourListView.getSelectionModel().getSelectedItems();
        viewModel.getTours().removeAll(selectedItems);
    }*/
}

