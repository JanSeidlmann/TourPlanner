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
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.util.OpenRouteService;
import org.example.tourplanner.TourPlannerApplication;
import org.example.tourplanner.util.MapGenerator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
        mainController = MainController.getInstance();
        selectedTourName.setText("Selected tour: " + mainController.getSelectedTourName());
        // this.webView.disableProperty().bindBidirectional(tour);
    }

    private double[] getCoordinates(String address) {
        if (address.equals("Vienna")) {
            return new double[]{48.210033, 16.363449};
        } else if (address.equals("Graz")) {
            return new double[]{47.070713, 15.439504};
        } else {
            throw new IllegalArgumentException("Address not recognized");
        }
    }

    private String generateLeafletMap(TourModel tour) {
        /*
        if (selectedTour == null) {
            return "<html><body><h1>No tour selected</h1></body></html>";
        }
        try {
//            double startLat = Double.parseDouble(selectedTour.getFromProperty().get().split(",")[0]);
//            double startLng = Double.parseDouble(selectedTour.getFromProperty().get().split(",")[1]);
//            double endLat = Double.parseDouble(selectedTour.getToProperty().get().split(",")[0]);
//            double endLng = Double.parseDouble(selectedTour.getToProperty().get().split(",")[1]);
//            String routeJson = OpenRouteService.getRoute(startLat, startLng, endLat, endLng);

            double[] fromCoords = getCoordinates(tour.getFromProperty().getValue());
            double[] toCoords = getCoordinates(tour.getToProperty().getValue());

            // Route von OpenRouteService abrufen
            String routeJson = OpenRouteService.getRoute(fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);

            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Leaflet Map with OpenRouteService</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet/dist/leaflet.css\" />\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div id=\"map\" style=\"width: 100%; height: 100%;\"></div>\n" +
                    "    <script src=\"https://unpkg.com/leaflet/dist/leaflet.js\"></script>\n" +
                    "    <script>\n" +
                    "        const map = L.map('map').setView([48.210033, 16.363449], 13);\n" +
                    "        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {\n" +
                    "            maxZoom: 19,\n" +
                    "        }).addTo(map);\n" +
                    "        const routeData = " + routeJson + ";\n" +
                    "        const coordinates = routeData.features[0].geometry.coordinates;\n" +
                    "        const latlngs = coordinates.map(coord => [coord[1], coord[0]]);\n" +
                    "        L.polyline(latlngs, { color: 'blue' }).addTo(map);\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<html><body><p>Error loading map</p></body></html>";
        }

         */
        return "";
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
                // log error
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
}