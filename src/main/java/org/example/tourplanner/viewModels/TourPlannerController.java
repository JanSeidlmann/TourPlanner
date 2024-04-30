package org.example.tourplanner.viewModels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TourPlannerController implements Initializable {

    private NewWindowController newWindowController;

    @FXML
    private ListView<String> tourListView;

    private CreateViewModel createViewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createViewModel = new CreateViewModel();
        tourListView.setItems(createViewModel.getTourList());
        newWindowController = new NewWindowController();
        newWindowController.setCallback(this::addTourToListView);
    }

    @FXML
    protected void openApplicationModalWindow(ActionEvent event) throws IOException {
        Stage currentStage = getRoot(event);
        newWindowController.show(currentStage);
    }

    private void addTourToListView(String tourName) {
        createViewModel.addTour(tourName);
    }

    private static Stage getRoot(ActionEvent event){
        Node root = (Node) event.getSource();
        return (Stage) root.getScene().getWindow();
    }

}