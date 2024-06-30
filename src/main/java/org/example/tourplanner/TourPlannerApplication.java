package org.example.tourplanner;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.tourplanner.PL.controller.MainController;

import java.io.File;
import java.io.IOException;

public class TourPlannerApplication extends Application {
    private static final File LEAFLET_HTML = new File(System.getProperty("user.dir"), "/src/main/resources/org/example/tourplanner/maps/leaflet.html");

    private static final String DARK_MODE_CSS = "/theme.css";

    private static HostServices hostService;

    private static Scene mainScene;

    @Override
    public void init() throws Exception {
        hostService = getHostServices();
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
        AnchorPane root = loader.load();
        MainController mainController = loader.getController();

        mainScene = new Scene(root, 900, 600);
        mainStage.setScene(mainScene);
        mainStage.setTitle("Tour Planner by Jan and Laura");
        mainStage.show();

        enableDarkMode(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void openTourMapInBrowser() {
        hostService.showDocument(LEAFLET_HTML.toURI().toString());
    }

    public static void enableDarkMode(boolean enable) {
        if (enable) {
            if (!mainScene.getStylesheets().contains(DARK_MODE_CSS)) {
                mainScene.getStylesheets().add(DARK_MODE_CSS);
            }
        } else {
            mainScene.getStylesheets().remove(DARK_MODE_CSS);
        }
    }
}