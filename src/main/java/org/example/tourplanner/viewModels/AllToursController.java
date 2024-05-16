package org.example.tourplanner.viewModels;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.tourplanner.Models.TourModel;
import org.example.tourplanner.TourPlannerApplication;
import org.example.tourplanner.models.TourModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllToursController implements Initializable {

    /*
    @FXML
    public ListView<String> tourListView;

     */
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

    private MainViewModel viewModel;

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

    @FXML
    protected void editTour(ActionEvent event) throws IOException {
        TourModel selectedTour = tourTableView.getSelectionModel().getSelectedItem();
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
        //ObservableList<String> selectedName = tourListView.getSelectionModel().getSelectedItems();
        ObservableList<TourModel> selectedItems = tourTableView.getSelectionModel().getSelectedItems();
        //viewModel.getTourNames().removeAll(selectedName);
        viewModel.getTours().removeAll(selectedItems);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
        nameColumn.setCellValueFactory(data -> data.getValue().getName());
        descriptionColumn.setCellValueFactory(data -> data.getValue().getTourDescription());
        fromColumn.setCellValueFactory(data -> data.getValue().getFrom());
        toColumn.setCellValueFactory(data -> data.getValue().getTo());
        transportTypeColumn.setCellValueFactory(data -> data.getValue().getTransportType());
        distanceColumn.setCellValueFactory(data -> data.getValue().getDistance().asObject());
        timeColumn.setCellValueFactory(data -> data.getValue().getTime());
        routeInformationColumn.setCellValueFactory(data -> data.getValue().getRouteInformation());

        //tourListView.setItems(viewModel.getTourNames());
        tourTableView.setItems(viewModel.getTours());
    }
}