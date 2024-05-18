package org.example.tourplanner.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.Getter;
import org.example.tourplanner.models.LogModel;
import org.example.tourplanner.models.TourModel;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class MainViewModel {

    private static MainViewModel instance;
    private final ObservableList<TourModel> tours = FXCollections.observableArrayList();
    private final ObservableList<String> tourNames = FXCollections.observableArrayList();
    private final ObservableList<LogModel> logs = FXCollections.observableArrayList();
    private final ObservableList<String> logNames = FXCollections.observableArrayList();

    // Privater Konstruktor für Singleton
    private MainViewModel() {}

    // Öffentliche Methode zur Abfrage der Singleton-Instanz
    public static MainViewModel getInstance() {
        if (instance == null) {
            instance = new MainViewModel();
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
