package org.example.tourplanner;

import javafx.stage.Stage;
import org.example.tourplanner.BL.models.TourModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class MainWindowTest extends ApplicationTest {
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
    void testLoginButtonEnabled() {
        verifyThat("#addTourButton", isEnabled());
    }

    @Test
    void testLoginWithValidCredentials() {
        clickOn("#addTourButton");

        verifyThat("#createButton", isVisible());
        verifyThat("#cancelButton", isVisible());
    }

    @Test
    void testLogoutButtonLogsOutUser() {
        testLoginWithValidCredentials();

        clickOn("#cancelButton");
        verifyThat("#addTourButton", isVisible());
    }

    @Test
    void testTourModelProperties() {
        TourModel tour = new TourModel("TourName", "Description", "From", "To", "TransportType", 10.5f, "Time", "RouteInformation");
        assertEquals("TourName", tour.getNameProperty().get());
        assertEquals("Description", tour.getTourDescriptionProperty().get());
        assertEquals("From", tour.getFromProperty().get());
        assertEquals("To", tour.getToProperty().get());
        assertEquals("TransportType", tour.getTransportTypeProperty().get());
        assertEquals(10.5f, tour.getDistanceProperty().get());
        assertEquals("Time", tour.getTimeProperty().get());
        assertEquals("RouteInformation", tour.getRouteInformationProperty().get());
    }
}
