package org.example.tourplanner.PL.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.tourplanner.BL.ITourService;
import org.example.tourplanner.BL.TourService;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

public class CreateTourViewModel implements Injectable {
    private final ITourService tourService;

    @Getter
    private ObservableList<TourModel> tourTableList;

    public CreateTourViewModel() {
        this.tourService = DefaultInjector.getService(TourService.class);
        this.tourTableList = FXCollections.observableArrayList();
        loadTours();
    }

    private void loadTours() {
        tourTableList.clear();
        var validUserModels = tourService.getAllTours();
        tourTableList.addAll(validUserModels);
    }

    public void addTour(TourModel tourModel) {
        tourService.addTour(tourModel);
        loadTours();
    }

    public void deleteTour(TourModel tourModel) {
        tourService.deleteTour(tourModel);
        loadTours();
    }
}
