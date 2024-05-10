package org.example.tourplanner.viewModels;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
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

    private MainViewModel viewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
        editTransportType.getItems().setAll("Car", "Bike", "Walk");

        TourModel newTour = new TourModel();
        editNameTextField.textProperty().bindBidirectional(newTour.getName());
        editDescriptionTextField.textProperty().bindBidirectional(newTour.getTourDescription());
        editFromTextField.textProperty().bindBidirectional(newTour.getFrom());
        editToTextField.textProperty().bindBidirectional(newTour.getTo());
        editTransportType.valueProperty().bindBidirectional(newTour.getTransportType());
        editDistanceTextField.textProperty().bindBidirectional(newTour.getDistance(), new NumberStringConverter());
        editTimeTextField.textProperty().bindBidirectional(newTour.getTime());
        editRouteInformationTextField.textProperty().bindBidirectional(newTour.getRouteInformation());
    }

    @FXML
    private void editTour() {
        TourModel tourModel = viewModel.getTours().get(0);

        FloatProperty distanceProperty = new SimpleFloatProperty(Float.parseFloat(editDistanceTextField.getText()));
        StringProperty nameProperty = new SimpleStringProperty(editNameTextField.getText());
        StringProperty descriptionProperty = new SimpleStringProperty(editDescriptionTextField.getText());
        StringProperty fromProperty = new SimpleStringProperty(editFromTextField.getText());
        StringProperty toProperty = new SimpleStringProperty(editToTextField.getText());
        StringProperty timeProperty = new SimpleStringProperty(editTimeTextField.getText());
        StringProperty transportTypeProperty = new SimpleStringProperty(editTransportType.getSelectionModel().getSelectedItem());
        StringProperty routeInformationProperty = new SimpleStringProperty(editRouteInformationTextField.getText());

        tourModel.setName(nameProperty);
        tourModel.setTourDescription(descriptionProperty);
        tourModel.setFrom(fromProperty);
        tourModel.setTo(toProperty);
        tourModel.setTime(timeProperty);
        tourModel.setDistance(distanceProperty);
        tourModel.setTransportType(transportTypeProperty);
        tourModel.setRouteInformation(routeInformationProperty);

        viewModel.addTour(tourModel);
        closeStage();
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
