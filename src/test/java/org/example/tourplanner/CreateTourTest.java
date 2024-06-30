package org.example.tourplanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.PL.controller.CreateTourController;
import org.example.tourplanner.PL.controller.MainController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

public class CreateTourTest extends ApplicationTest {

    private CreateTourController controller;

    private MainController mainController = new MainController();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/createTour.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        controller = loader.getController();
    }

    @BeforeEach
    public void setUp() {
        controller.initialize(null, null);
    }

    @Test
    void testDefaultRouteInformationOnCreateButtonClicked() {
        clickOn(controller.nameTextField).write("TestName");
        clickOn(controller.routeInformationTextField).write("map-placeholder.png");
        clickOn(controller.createButton);
        assertEquals("map-placeholder.png", controller.routeInformationTextField.getText());
    }

    @Test
    void testAddTourOnCreateButtonClicked() {
        // Set up text fields
        clickOn(controller.nameTextField).write("Tour1");
        clickOn(controller.descriptionTextField).write("This is a tour");
        clickOn(controller.fromTextField).write("Start");
        clickOn(controller.toTextField).write("End");
        clickOn(controller.distanceTextField).write("10");
        clickOn(controller.timeTextField).write("1 hour");
        clickOn(controller.routeInformationTextField).write("route.png");

        // Click create button
        clickOn(controller.createButton);

        // Check if tour was added
        assertTrue(mainController.getTourNames().contains("Tour1"));
    }

    @Test
    void testCancelButtonClicked() {
        Window window = controller.createButton.getScene().getWindow();
        clickOn(controller.cancelButton);
        assertFalse(window.isShowing());
    }
}

