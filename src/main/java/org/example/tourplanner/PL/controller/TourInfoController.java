package org.example.tourplanner.PL.controller;

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
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.IPDFService;
import org.example.tourplanner.BL.PDFService;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.TourPlannerApplication;
import org.example.tourplanner.util.MapGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
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

    private IPDFService pdfService;

    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = MainController.getInstance();
        selectedTourName.setText("Selected tour: " + mainController.getSelectedTourName());
        this.pdfService = DefaultInjector.getService(PDFService.class);
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
            log.warn("No tour selected for editing.");
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

            // with help from team5 - https://github.com/helhar1234/TourPlanner_SWEN2_Team5
            try {
                String mapDir = System.getProperty("user.dir") + "/src/main/resources/org/example/tourplanner/maps";
                String filename = "map_" + tour.getId();
                File mapFile = new File(mapDir, filename);

                if (!mapFile.exists()) {
                    filename = MapGenerator.getMapImage(tour);
                    mapFile = new File(mapDir, filename);
                }

                Image mapImage = new Image(mapFile.toURI().toString());
                tourMap.setPreserveRatio(true);
                tourMap.setImage(mapImage);
                tourMap.setOnMouseClicked(event -> MapGenerator.openTourMapInBrowser(tour));
            } catch (IOException e) {
                log.error("An error occurred while setting the tourMap, error: " + e);
            }
        } else {
            nameField.setText("");
            tourDescriptionField.setText("");
            fromField.setText("");
            toField.setText("");
            transportTypeField.setText("");
            distanceField.setText("");
            timeField.setText("");
            tourMap.setImage(null);
        }
        selectedTourName.setText("Selected tour: " + mainController.getSelectedTourName());
    }

    @FXML
    public void generatePDF(ActionEvent actionEvent) {
        try{
            pdfService.createUserListPDF("TourList.pdf", selectedTour);
            log.info("PDF of tour [id:" + selectedTour.getId() + "] generated successfully.");
        } catch (Exception e) {
            log.error("An error occurred while generating PDF of tour [id:" + selectedTour.getId() + "], error: " + e);
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleExportButton(ActionEvent event) {
        File file = new File("exported_tour.csv");

        try {
            exportTourToCsv(selectedTour, file);
            log.info("Tour [id:" + selectedTour.getId() + "] exported to [path:" + file.getAbsolutePath() + "]");
        } catch (IOException e) {
            log.error("An error occurred while exporting tour [id:" + selectedTour.getId() + "], error: " + e);
        }
    }


    @FXML
    public void exportTourToCsv(TourModel selectedTour, File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            if (selectedTour != null) {
                writer.println(selectedTour.getName() + "," + selectedTour.getTourDescription() + ","
                        + selectedTour.getFrom() + "," + selectedTour.getTo() + ","
                        + selectedTour.getTransportType() + "," + selectedTour.getDistance() + ","
                        + selectedTour.getTime() + "," + selectedTour.getRouteInformation());
            }
        }
    }
}