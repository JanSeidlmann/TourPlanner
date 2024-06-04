package org.example.tourplanner;

import org.example.tourplanner.models.TourModel;

import java.util.List;

public interface ITourService {
    List<TourModel> getAllUsers();
    void addUser(TourModel tour);
    void deleteUser(TourModel tourModel);
}
