package org.example.tourplanner;

import org.example.tourplanner.models.TourModel;

import java.util.List;

public class TourService implements ITourService {
    private final TourDAO tourDAO;

    public TourService() {
        this.tourDAO = new TourDAO();
    }

    @Override
    public List<TourModel> getAllUsers(){
        return tourDAO.findALl();
    }

    @Override
    public void addTour(TourModel tour){
        tourDAO.save(tour);
    }
    @Override
    public void deleteTour(TourModel tourModel){
        tourDAO.delete(tourModel);
    }
}
