package org.example.tourplanner.viewModels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.tourplanner.models.TourModel;
import org.example.tourplanner.TourPlannerApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TourInfoController implements Initializable {

    @FXML
    public Label selectedTourName;
    @FXML
    private TextField nameField = new TextField();
    @FXML
    private TextField tourDescriptionField = new TextField();
    @FXML
    private TextField fromField = new TextField();
    @FXML
    private TextField toField = new TextField();
    @FXML
    private TextField transportTypeField = new TextField();
    @FXML
    private TextField distanceField = new TextField();
    @FXML
    private TextField timeField = new TextField();
    @FXML
    private TextField routeInformationField = new TextField();


    private TourModel selectedTour;
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();
    }

    @FXML
    protected void editTour(ActionEvent event) throws IOException {
        if (selectedTour != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("/org/example/tourplanner/editTour.fxml"));
            Parent root = fxmlLoader.load();

            EditTourController editTourController = fxmlLoader.getController();
            editTourController.setTourModel(selectedTour);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Tour");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a tour to edit.");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeTour() {
    }

    @FXML
    private void addLog(ActionEvent actionEvent) {
    }

    public void setTourInfo(TourModel tour) {
        System.out.println("Setting tour info to tour: " + tour);
        if (tour != null) {
            nameField.setText(String.valueOf(tour.getName()));
            tourDescriptionField.setText(String.valueOf(tour.getTourDescription()));
            fromField.setText(String.valueOf(tour.getFrom()));
            toField.setText(String.valueOf(tour.getTo()));
            transportTypeField.setText(String.valueOf(tour.getTransportType()));
            distanceField.setText(String.valueOf(tour.getDistance()));
            timeField.setText(String.valueOf(tour.getTime()));
            routeInformationField.setText(String.valueOf(tour.getRouteInformation()));
            System.out.println("All set");
        }
    }
}