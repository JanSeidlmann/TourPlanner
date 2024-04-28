package org.example.tourplanner.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class TourPlannerController {

    @FXML
    static NewWindowController newWindowController = new NewWindowController();

    @FXML
    protected void openApplicationModalWindow(ActionEvent event) throws IOException {
        Stage currentStage = getRoot(event);
        newWindowController.show(currentStage);
    }

    private static Stage getRoot(ActionEvent event){
        Node root = (Node) event.getSource();
        return (Stage) root.getScene().getWindow();
    }

}