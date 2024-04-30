package org.example.tourplanner.viewModels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewWindowController implements Initializable {
    @FXML
    public TextField nameTextField;

    @FXML
    private Label labelNewWindow;

    @FXML
    private ChoiceBox<String> transportType;

    private Stage newStage;

    public void setStage(Stage stage) {
        this.newStage = stage;
    }

    @FXML
    private void closeWindow() {
        newStage.close();
    }

    public interface AddTourCallback {
        void addTour(String tourName);
    }

    private AddTourCallback callback;


    public void setCallback(AddTourCallback callback) {
        this.callback = callback;
    }

    public void addTour() {
        String name = nameTextField.getText().trim();
        if (!name.isEmpty() && callback != null) {
            callback.addTour(name);
            nameTextField.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transportType.getItems().addAll("Car", "Bike", "Walk");
        CreateViewModel createViewModel = new CreateViewModel();
    }

    protected void show(Stage currentStage) throws IOException {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(NewWindowController.class.getResource("/org/example/tourplanner/newWindow.fxml"));
        fl.load();
        Parent root = fl.getRoot();
        newStage = new Stage();
        newStage.initOwner(currentStage);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        Scene scene = new Scene(root, 565, 400 );
        newStage.setScene(scene);
        newStage.setTitle("Create Tour");
        newStage.show();
        ((NewWindowController) fl.getController()).labelNewWindow.setText("Create Tour");
        ((NewWindowController) fl.getController()).setStage(newStage);
        ((NewWindowController) fl.getController()).setCallback(callback);
    }
}

