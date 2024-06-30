package org.example.tourplanner;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.PL.controller.TourInfoController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.hamcrest.Matchers.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static org.testfx.util.NodeQueryUtils.hasText;

public class TourInfoTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        new TourPlannerApplication().start(stage);
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupStage(Stage::show);
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testTourInfo() {
        TourModel selectedTour = new TourModel();
        selectedTour.setName("ich bin json");
        TourInfoController tourInfoController = new TourInfoController();
        // Annahme, dass Ihre Anwendung die Tourinformationen entsprechend setzt
        tourInfoController.setTourInfo(selectedTour);

        // Hier überprüfen wir, ob das GUI-Element '#nameField' den Text 'ich bin json' enthält
        verifyThat("#nameField", equalTo("Name: ich bin json"));

    }

}
