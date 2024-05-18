package org.example.tourplanner.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.tourplanner.models.LogModel;
import org.example.tourplanner.models.TourModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class MainController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab allToursTab;
    @FXML
    private Tab tourInfoTab;
    @FXML
    private AllToursController allToursController = new AllToursController();
    @FXML
    private TourInfoController tourInfoController = new TourInfoController();

    private static MainController instance;
    private final ObservableList<TourModel> tours = FXCollections.observableArrayList();
    private final ObservableList<String> tourNames = FXCollections.observableArrayList();
    private final ObservableList<LogModel> logs = FXCollections.observableArrayList();
    private final ObservableList<String> logNames = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing MainController...");
        System.out.println("tabPane: " + tabPane);
        System.out.println("tourInfoTab: " + tourInfoTab);
        if (allToursController != null) {
            allToursController.setMainController(this);
        }
        loadTourInfoView();
        System.out.println("tourInfoController: " + tourInfoController);
    }

    private void loadTourInfoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tourplanner/tour-info.fxml"));
            AnchorPane tourInfoContent = loader.load();
            tourInfoTab.setContent(tourInfoContent);
            tourInfoController = loader.getController();
            System.out.println("Loaded TourInfo view. tourInfoController: " + tourInfoController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToTourInfoTab(TourModel selectedTour) {
        if (tourInfoController != null) {
            System.out.println("Switching to Tour Info tab with selected tour: " + selectedTour);
            tourInfoController.setTourInfo(selectedTour);
            System.out.println("back in switchToTourInfoTab after tourInfoController.setTourInfo()");
            tabPane.getSelectionModel().select(tourInfoTab);
        } else {
            System.out.println("tourInfoController is null!");
        }
    }


    // Privater Konstruktor für Singleton
    public MainController() {
        instance = this;
    }

    // Öffentliche Methode zur Abfrage der Singleton-Instanz
    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void addTour(TourModel tour) {
        tours.add(tour);
    }

    public void addTourName(String tourName) {
        tourNames.add(tourName);
    }

    public void addLog(LogModel log) {
        logs.add(log);
    }

    public void addLogName(String logName) {
        tourNames.add(logName);
    }


}
