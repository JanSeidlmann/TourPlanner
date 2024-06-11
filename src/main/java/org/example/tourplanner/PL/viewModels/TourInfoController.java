package org.example.tourplanner.PL.viewModels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.TourPlannerApplication;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TourInfoController implements Initializable, Injectable {

    @FXML
    public Label selectedTourName;
    @FXML
    private Label nameField = new Label();
    @FXML
    private Label tourDescriptionField = new Label();
    @FXML
    private Label fromField = new Label();
    @FXML
    private Label toField = new Label();
    @FXML
    private Label transportTypeField = new Label();
    @FXML
    private Label distanceField = new Label();
    @FXML
    private Label timeField = new Label();
    @FXML
    private Label routeInformationField = new Label();
    @FXML
    private ImageView tourMap;

    @Setter
    private TourModel selectedTour;
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance(); // Passt das so oder gehört da auch mit DefaultInjector?????????????????????????
        selectedTourName.setText("Selected tour: " + mainController.getSelectedTourName());
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
        mainController.removeTour(selectedTour);
        mainController.switchToAllToursTab();
    }

    @FXML
    protected void createLog(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("/org/example/tourplanner/createLog.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Log");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void setTourInfo(TourModel tour) {
        if (tour != null) {
            nameField.setText("Name: " + tour.getNameProperty().getValue());
            tourDescriptionField.setText("Description: " + tour.getTourDescriptionProperty().getValue());
            fromField.setText("From: " + tour.getFromProperty().getValue());
            toField.setText("To: " + tour.getToProperty().getValue());
            transportTypeField.setText("TransportType: " + tour.getTransportTypeProperty().getValue());
            distanceField.setText("Distance: " + tour.getDistanceProperty().getValue());
            timeField.setText("Time: " + tour.getTimeProperty().getValue());
            routeInformationField.setText("Route information: see map");

            String imagePath = "/org/example/tourplanner/img/" + tour.getRouteInformationProperty().getValue();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            tourMap.setImage(image);
        } else {
            nameField.setText("");
            tourDescriptionField.setText("");
            fromField.setText("");
            toField.setText("");
            transportTypeField.setText("");
            distanceField.setText("");
            timeField.setText("");
            routeInformationField.setText("");
            tourMap.setImage(null);
        }
        selectedTourName.setText("Selected tour: " + mainController.getSelectedTourName());
    }
}