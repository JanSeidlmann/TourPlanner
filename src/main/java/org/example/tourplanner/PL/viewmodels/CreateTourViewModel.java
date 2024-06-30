package org.example.tourplanner.PL.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.ITourService;
import org.example.tourplanner.BL.TourService;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

@Slf4j
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
        var validTourModels = tourService.getAllTours();
        tourTableList.addAll(validTourModels);
    }

    public void addTour(TourModel tourModel) {
        tourService.addTour(tourModel);
        log.info("Success adding new tour [id:" + tourModel.getId() + "].");
        loadTours();
    }

    public void deleteTour(TourModel tourModel) {
        tourService.deleteTour(tourModel);
        log.info("Success deleting tour [id:" + tourModel.getId() + "].");
        loadTours();
    }
}
