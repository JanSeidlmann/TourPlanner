package org.example.tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.tourplanner.PL.viewModels.MainController;

import java.io.IOException;

public class TourPlannerApplication extends Application {

    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
        AnchorPane root = loader.load();
        MainController mainController = loader.getController();

        Scene scene = new Scene(root, 900, 600);
        mainStage.setScene(scene);
        mainStage.setTitle("Tour Planner by Jan and Laura");
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}