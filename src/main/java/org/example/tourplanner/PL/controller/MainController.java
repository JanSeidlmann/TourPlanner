package org.example.tourplanner.PL.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DAL.repositories.TourDAO;
import org.example.tourplanner.DAL.repositories.LogDAO;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.PL.viewmodels.CreateLogViewModel;
import org.example.tourplanner.PL.viewmodels.CreateTourViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Getter
public class MainController implements Initializable, Injectable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab allToursTab;
    @FXML
    private Tab tourInfoTab;
    @FXML
    private Tab logsTab;

    @FXML
    private AllToursController allToursController;
    @FXML
    private TourInfoController tourInfoController;
    @FXML
    private LogController logController;

    private static MainController instance;
    private final TourDAO tourDAO = new TourDAO();
    private final LogDAO logDAO = new LogDAO();

    private final List<TourModel> initialTours = tourDAO.findAll();
    private final ObservableList<TourModel> tours = FXCollections.observableArrayList(initialTours);
    private final ObservableList<String> tourNames = FXCollections.observableArrayList();
    private final List<LogModel> initialLogs = logDAO.findALl();
    private ObservableList<LogModel> logs = FXCollections.observableArrayList(initialLogs);

    private TourModel selectedTour;
    private LogModel selectedLog;
    private final CreateTourViewModel createTourViewModel;
    private final CreateLogViewModel createLogViewModel;

    public MainController(){
        this.allToursController = DefaultInjector.getService(AllToursController.class);
        this.tourInfoController = DefaultInjector.getService(TourInfoController.class);
        this.logController = DefaultInjector.getService(LogController.class);
        this.createTourViewModel = DefaultInjector.getService(CreateTourViewModel.class);
        this.createLogViewModel = DefaultInjector.getService(CreateLogViewModel.class);
        instance = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (allToursController != null) {
            allToursController.setMainController(this);
        }
        if (logController != null) {
            logController.setMainController(this);
        }
        loadTourInfoView();
        loadLogView();
    }

    // Öffentliche Methode zur Abfrage der Singleton-Instanz
    public static MainController getInstance() {
        if (instance == null) {
            instance = DefaultInjector.getService(MainController.class);
        }
        return instance;
    }

    private void loadTourInfoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/tour-info.fxml"));
            AnchorPane tourInfoContent = loader.load();
            tourInfoTab.setContent(tourInfoContent);
            tourInfoController = loader.getController();
        } catch (IOException e) {
            log.error("An error occurred while loading TourInfoView, error: " + e);
        }
    }

    private void loadLogView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/logs.fxml"));
            AnchorPane logsContent = loader.load();
            logsTab.setContent(logsContent);
            logController = loader.getController();
        } catch (IOException e) {
            log.error("An error occurred while loading LogView, error: " + e);
        }
    }

    public  void switchToAllToursTab() {
        if (selectedTour != null) {
            tourInfoController.setSelectedTour(selectedTour);

        } else {
            tourInfoController.setSelectedTour(null);
        }
        logController.setLogsOfTour();
        tabPane.getSelectionModel().select(allToursTab);
    }

    public void switchToTourInfoTab(TourModel selectedTour) {
        this.selectedTour = selectedTour;
        if (tourInfoController != null) {
            tourInfoController.setSelectedTour(selectedTour);
            tourInfoController.setTourInfo(selectedTour);
            this.logs = FXCollections.observableArrayList(logDAO.findByTour(selectedTour));
            logController.setLogsOfTour();
            tabPane.getSelectionModel().select(tourInfoTab);
        } else {
            log.error("TourInfoController is null.");
        }
    }

    public void switchToLogsTab() {
        if (logController != null) {
            logController.setLogsOfTour();
            tabPane.getSelectionModel().select(logsTab);
        } else {
            log.error("LogController is null.");
        }
    }

    public void updateSelectedTour(TourModel tour) {
        setSelectedTour(tour);
        tourInfoController.setTourInfo(selectedTour);
        logController.setLogsOfTour();
    }

    public void setSelectedTour(TourModel tour) {
        selectedTour = tour;
    }


    public void addTour(TourModel tour) {
        tours.add(tour);
    }

    public void addTourName(String tourName) {
        tourNames.add(tourName);
    }

    public void removeTour(TourModel tour) {
        tours.remove(tour);
        tourNames.remove(tour.getName().toString());
        selectedTour = null;
        tourInfoController.setTourInfo(null);
        logController.setLogsOfTour();
        createTourViewModel.deleteTour(tour);
    }

    public String getSelectedTourName() {
        if (selectedTour != null) {
            return selectedTour.getNameProperty().getValue();
        } else {
            log.warn("SelectedTour is null.");
            return "null";
        }
    }

    public void removeLog(LogModel log) {
        logController.setLogsOfTour();
        createLogViewModel.deleteLog(log);
    }

}
