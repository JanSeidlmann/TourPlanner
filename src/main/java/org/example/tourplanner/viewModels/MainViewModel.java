package org.example.tourplanner.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.tourplanner.models.TourModel;

@Getter
public class MainViewModel {

    private static MainViewModel instance;
    private final ObservableList<TourModel> tours = FXCollections.observableArrayList();
    private final ObservableList<String> tourNames = FXCollections.observableArrayList();

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
}
