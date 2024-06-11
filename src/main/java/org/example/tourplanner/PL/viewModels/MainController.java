package org.example.tourplanner.PL.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private final ObservableList<TourModel> tours = FXCollections.observableArrayList();
    private final ObservableList<String> tourNames = FXCollections.observableArrayList();
    //private final ObservableList<LogModel> logs = FXCollections.observableArrayList();
    private final ObservableList<String> logNames = FXCollections.observableArrayList();

    private TourModel selectedTour;
    private LogModel selectedLog;
    private final CreateTourViewModel createTourViewModel;

    public MainController(){
        this.allToursController = DefaultInjector.getService(AllToursController.class);
        this.tourInfoController = DefaultInjector.getService(TourInfoController.class);
        this.logController = DefaultInjector.getService(LogController.class);
        this.createTourViewModel = DefaultInjector.getService(CreateTourViewModel.class);
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
            e.printStackTrace();
        }
    }

    private void loadLogView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/logs.fxml"));
            AnchorPane logsContent = loader.load();
            logsTab.setContent(logsContent);
            logController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
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
            logController.setLogsOfTour();
            tabPane.getSelectionModel().select(tourInfoTab);
        } else {
            System.out.println("tourInfoController is null!");
        }
    }

    public void switchToLogsTab() {
        if (logController != null) {
            logController.setLogsOfTour();
            tabPane.getSelectionModel().select(logsTab);
        } else {
            System.out.println("logController is null");
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
            return "null";
        }
    }

    public ObservableList<LogModel> getLogs() {
        if (selectedTour != null) {
            return selectedTour.getLogs();
        } else {
            return null;
        }
    }

    public void addLog(LogModel log) {
        selectedTour.addLog(log);
    }

    public void removeLog(LogModel log) {
        selectedTour.removeLog(log);
        logController.setLogsOfTour();
    }

    public void addLogName(String logName) {
        tourNames.add(logName);
    }


}
