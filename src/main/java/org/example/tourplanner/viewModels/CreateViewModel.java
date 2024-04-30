package org.example.tourplanner.viewModels;


import javafx.collections.ObservableList;
import org.example.tourplanner.models.TourModel;

public class CreateViewModel {

    private final TourModel tourModel;

    public ObservableList<String> getTourList() {
        return tourModel.getTourList();
    }

    public CreateViewModel() {
        tourModel = new TourModel();
    }

    public void addTour(String person) {
        tourModel.addTour(person);
    }

}
