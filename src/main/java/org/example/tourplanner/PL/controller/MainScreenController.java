package org.example.tourplanner.PL.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import org.example.tourplanner.TourPlannerApplication;

import java.io.IOException;
import java.util.Objects;

public class MainScreenController {
    @FXML
    private VBox mainScreenContent;

    public void changeMainScreenContent(String fxmlFile) {
        try {
            mainScreenContent.getChildren().clear();
            mainScreenContent.getChildren().add(FXMLLoader.load(Objects.requireNonNull(TourPlannerApplication.class.getResource(fxmlFile))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
