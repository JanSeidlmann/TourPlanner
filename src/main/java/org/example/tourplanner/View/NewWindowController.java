package org.example.tourplanner.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewWindowController implements Initializable {

    @FXML
    private Label labelNewWindow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    protected void show(Stage currentStage) throws IOException {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(NewWindowController.class.getResource("/org/example/tourplanner/newWindow.fxml"));
        fl.load();
        Parent root = fl.getRoot();
        Stage newStage = new Stage();
        newStage.initOwner(currentStage);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        Scene scene = new Scene(root, 500, 300 );
        newStage.setScene(scene);
        newStage.setTitle("Application Modality");
        newStage.show();
        ((NewWindowController) fl.getController()).labelNewWindow.setText("Application Modality");
    }
}

