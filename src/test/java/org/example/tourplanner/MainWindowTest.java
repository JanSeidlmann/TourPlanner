package org.example.tourplanner;

import javafx.stage.Stage;
import org.example.tourplanner.models.TourModel;
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
        assertEquals("TourName", tour.getName().get());
        assertEquals("Description", tour.getTourDescription().get());
        assertEquals("From", tour.getFrom().get());
        assertEquals("To", tour.getTo().get());
        assertEquals("TransportType", tour.getTransportType().get());
        assertEquals(10.5f, tour.getDistance().get());
        assertEquals("Time", tour.getTime().get());
        assertEquals("RouteInformation", tour.getRouteInformation().get());
    }
}
