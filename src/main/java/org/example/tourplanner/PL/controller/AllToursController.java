package org.example.tourplanner.PL.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.tourplanner.BL.ITourService;
import org.example.tourplanner.BL.TourService;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DAL.repositories.TourDAO;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.TourPlannerApplication;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    private ITourService tourService;

    private TourDAO tourDAO;

    private final ObservableList<TourModel> tourList = FXCollections.observableArrayList();

    @Setter
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourService = DefaultInjector.getService(TourService.class);
        this.tourDAO = DefaultInjector.getService(TourDAO.class);
        mainController = MainController.getInstance();

        nameColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
        descriptionColumn.setCellValueFactory(data -> data.getValue().getTourDescriptionProperty());
        fromColumn.setCellValueFactory(data -> data.getValue().getFromProperty());
        toColumn.setCellValueFactory(data -> data.getValue().getToProperty());
        transportTypeColumn.setCellValueFactory(data -> data.getValue().getTransportTypeProperty());
        distanceColumn.setCellValueFactory(data -> data.getValue().getDistanceProperty().asObject());
        timeColumn.setCellValueFactory(data -> data.getValue().getTimeProperty());
        routeInformationColumn.setCellValueFactory(data -> data.getValue().getRouteInformationProperty());

        List<TourModel> existingTours = this.tourService.getAllTours();
        tourList.addAll(existingTours);
        tourTableView.setItems(tourList);
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

    // Methode zum Aktualisieren der Tourliste
    public void updateTourList() {
        // Reload tours after window is closed
        tourList.clear();
        tourList.addAll(tourService.getAllTours());
        tourTableView.setItems(tourList);
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

        updateTourList();

    }

    @FXML
    protected void importTour(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                importTourFromCsv(selectedFile);
                System.out.println("Tour erfolgreich importiert aus " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error importing tour from CSV file.");
                alert.showAndWait();
            }
        }
    }


    @FXML
    protected void importTourFromCsv(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            TourModel currentTour;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Handle Tour
                String name = values[0];
                String description = values[1];
                String from = values[2];
                String to = values[3];
                String transportType = values[4];
                float distance = Float.parseFloat(values[5]);
                String time = values[6];
                String routeInformation = values[7];

                currentTour = new TourModel(name, description, from, to, transportType, distance, time, routeInformation);
                tourList.add(currentTour);
                tourDAO.save(currentTour);
            }

            tourTableView.setItems(tourList);
        } catch (IOException e) {
            throw e;
        }
    }
}