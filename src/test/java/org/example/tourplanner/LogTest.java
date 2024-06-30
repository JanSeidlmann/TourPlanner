package org.example.tourplanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.PL.controller.LogController;
import org.example.tourplanner.PL.controller.MainController;
import org.example.tourplanner.PL.controller.EditLogController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LogTest extends ApplicationTest {
    private LogController logController;
    private MainController mainController;
    private EditLogController editLogController;

    @Override
    public void start(Stage stage) throws Exception {
        new TourPlannerApplication().start(stage);
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupStage(Stage::show);
        logController = new LogController();
        mainController = mock(MainController.class);
        logController.setMainController(mainController);
        logController.logTableView = new TableView<>();
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testSetMainController() {
        logController.setMainController(mainController);
        assertEquals(mainController, logController.mainController);
    }

    @Test
    void testDeleteLogRemovesSelectedLog() {
        LogModel log = new LogModel();
        ObservableList<LogModel> logs = FXCollections.observableArrayList(log);
        logController.logTableView.setItems(logs);
        logController.logTableView.getSelectionModel().select(log);

        logController.deleteLog(new ActionEvent());

        verify(mainController, times(1)).removeLog(log);
    }

    @Test
    void testDeleteLog() {
        LogModel mockLog = mock(LogModel.class);
        TableView<LogModel> logTableView = new TableView<>();
        logTableView.getItems().add(mockLog);
        logTableView.getSelectionModel().select(mockLog);

        logController.logTableView = logTableView;
        logController.deleteLog(new ActionEvent());

        verify(mainController, times(1)).removeLog(mockLog);
    }

    @Test
    void testInitializeNewTableView() {
        FxRobot robot = new FxRobot();
        assertNotNull(logController.logTableView);
    }

    @Test
    void testSwitchToAllToursTab() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#allToursTab");
        assertTrue(robot.lookup("#allToursTab").query().isVisible());
    }

    @Test
    void testSwitchToTourInfoTab() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#tourInfoTab");
        assertTrue(robot.lookup("#tourInfoTab").query().isVisible());
    }

    @Test
    void testSwitchLogTab() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#logTab");
        assertTrue(robot.lookup("#logTab").query().isVisible());
    }

    @Test
    void testDeleteNewLog() {
        LogModel logModel = new LogModel("DateTime", "Comment", 1, 10.0f, "TotalTime", 3, new TourModel());
        mainController.addLog(logModel);

        FxRobot robot = new FxRobot();
        robot.clickOn("#deleteLogButton");

        assertFalse(mainController.getLogs().contains(logModel));
    }

    @Test
    void testEditLog() {
        // Setze zuerst das LogModel in EditLogController
        LogModel logModel = new LogModel("DateTime", "Comment", 1, 10.0f, "TotalTime", 3, new TourModel());
        editLogController.setLogModel(logModel);

        // Simuliere die Bearbeitung des Logs und überprüfe die Aktualisierung
        FxRobot robot = new FxRobot();
        robot.clickOn("#editDateTimeTextField").write("NewDateTime");
        robot.clickOn("#editLogButton");

        // Überprüfe, ob das Log erfolgreich bearbeitet wurde
        assertEquals("NewDateTime", logModel.getDateTimeProperty().get());
    }

}
