package org.example.tourplanner;

import org.example.tourplanner.models.TourModel;

import java.util.List;

public interface ITourService {
    List<TourModel> getAllUsers();
    void addTour(TourModel tour);
    void deleteTour(TourModel tourModel);
}
