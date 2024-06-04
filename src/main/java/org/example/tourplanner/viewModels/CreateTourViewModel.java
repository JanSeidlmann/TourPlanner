package org.example.tourplanner.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.tourplanner.ITourService;
import org.example.tourplanner.TourService;
import org.example.tourplanner.models.TourModel;

public class CreateTourViewModel {
    private final ITourService tourService;

    @Getter
    private ObservableList<TourModel> tourTableList;

    public CreateTourViewModel() {
        this.tourService = new TourService();
        this.tourTableList = FXCollections.observableArrayList();
        loadTours();
    }

    private void loadTours() {
        tourTableList.clear();
        var validUserModels = tourService.getAllUsers();
        tourTableList.addAll(validUserModels);
    }

    public void addTour(TourModel tourModel) {
        tourService.addUser(tourModel);
        loadTours();
    }

    public void deleteTour(TourModel tourModel) {
        tourService.deleteUser(tourModel);
        loadTours();
    }
}
