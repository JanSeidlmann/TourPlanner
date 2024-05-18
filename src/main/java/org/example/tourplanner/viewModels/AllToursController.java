package org.example.tourplanner.viewModels;

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
import org.example.tourplanner.models.TourModel;
import org.example.tourplanner.TourPlannerApplication;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllToursController implements Initializable {

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

    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();

        nameColumn.setCellValueFactory(data -> data.getValue().getName());
        descriptionColumn.setCellValueFactory(data -> data.getValue().getTourDescription());
        fromColumn.setCellValueFactory(data -> data.getValue().getFrom());
        toColumn.setCellValueFactory(data -> data.getValue().getTo());
        transportTypeColumn.setCellValueFactory(data -> data.getValue().getTransportType());
        distanceColumn.setCellValueFactory(data -> data.getValue().getDistance().asObject());
        timeColumn.setCellValueFactory(data -> data.getValue().getTime());
        routeInformationColumn.setCellValueFactory(data -> data.getValue().getRouteInformation());

        tourTableView.setItems(mainController.getTours());
        tourTableView.setOnMouseClicked(this::handleRowClick);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        System.out.println("MainController set in AllToursController");
    }

    private void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Double-click detected
            TourModel selectedTour = tourTableView.getSelectionModel().getSelectedItem();
            if (selectedTour != null) {
                System.out.println("Tour selected: " + selectedTour);
                mainController.switchToTourInfoTab(selectedTour);
            } else {
                System.out.println("No tour selected.");
            }
        }
    }

    @FXML
    protected void createTour(ActionEvent event) throws IOException {
        // Verwende den ClassLoader, der die MainApp geladen hat, um die Ressource zu bekommen
        FXMLLoader fxmlLoader = new FXMLLoader(TourPlannerApplication.class.getResource("/org/example/tourplanner/createTour.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Macht das Fenster modal
        stage.setTitle("Add New Tour");
        stage.setScene(new Scene(root));
        stage.showAndWait(); // Zeigt das Fenster und wartet, bis es geschlossen wird
    }


}