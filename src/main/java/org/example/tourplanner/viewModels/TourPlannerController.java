package org.example.tourplanner.viewModels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.tourplanner.TourPlannerApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TourPlannerController implements Initializable {

    @FXML
    public ListView<String> tourListView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel = MainViewModel.getInstance();
        tourListView.setItems(viewModel.getTourNames());
    }
}