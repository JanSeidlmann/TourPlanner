package org.example.tourplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.Objects;

public class TourPlannerApplication extends Application {

    @Getter
    private static Stage stage;

    @Override
    public void start(Stage mainStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-screen.fxml")));
        mainStage.setScene(new Scene(root, 600, 400));
        mainStage.setTitle("Tour Planner by Jan and Laura");
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void changeScene(String fxml) throws IOException{
        stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml))));
    }

}