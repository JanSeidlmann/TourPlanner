package org.example.tourplanner.PL.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.TourPlannerApplication;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllToursController implements Initializable, Injectable {

    @FXML
    public TableView<TourModel> tourTableView;

    @FXML
    private TableColumn<TourModel, String> nameColumn;
    @FXML
    private TableColumn<TourModel, String> descriptionColumn;
    @FXML
    private TableColumn<TourModel, String> fromColumn;
    @FXML
    private TableColumn<TourModel, String> toColumn;
    @FXML
    private TableColumn<TourModel, String> transportTypeColumn;
    @FXML
    private TableColumn<TourModel, Float> distanceColumn;
    @FXML
    private TableColumn<TourModel, String> timeColumn;
    @FXML
    private TableColumn<TourModel, String> routeInformationColumn;

    @Setter
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();

        nameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
        descriptionColumn.setCellValueFactory(data -> data.getValue().getTourDescriptionProperty());
        fromColumn.setCellValueFactory(data -> data.getValue().getFromProperty());
        toColumn.setCellValueFactory(data -> data.getValue().getToProperty());
        transportTypeColumn.setCellValueFactory(data -> data.getValue().getTransportTypeProperty());
        distanceColumn.setCellValueFactory(data -> data.getValue().getDistanceProperty().asObject());
        timeColumn.setCellValueFactory(data -> data.getValue().getTimeProperty());
        routeInformationColumn.setCellValueFactory(data -> data.getValue().getRouteInformationProperty());

        tourTableView.setItems(mainController.getTours());
        tourTableView.setOnMouseClicked(this::handleRowClick);
    }

    private void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Double-click detected
            TourModel selectedTour = tourTableView.getSelectionModel().getSelectedItem();
            if (selectedTour != null) {
                mainController.switchToTourInfoTab(selectedTour);
            } else {
                System.out.println("No tour selected.");
            }
        }
    }

    // Beim Create haben wir Hilfe von Team 5 erhalten. Ist mit Lektor abgesprochen.
    @FXML
    protected void createTour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("/org/example/tourplanner/createTour.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Macht das Fenster modal
        stage.setTitle("Add New Tour");
        stage.setScene(new Scene(root));
        stage.showAndWait(); // Zeigt das Fenster und wartet, bis es geschlossen wird
    }


}