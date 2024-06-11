package org.example.tourplanner.BL;

import org.example.tourplanner.BL.models.TourModel;

import java.util.List;

public interface ITourService {
    List<TourModel> getAllUsers();
    void addTour(TourModel tour);
    void deleteTour(TourModel tourModel);
}
