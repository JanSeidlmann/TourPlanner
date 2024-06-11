package org.example.tourplanner.BL;

import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DAL.repositories.TourDAO;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

import java.util.List;

public class TourService implements ITourService, Injectable {
    private final TourDAO tourDAO;

    public TourService() {
        this.tourDAO = DefaultInjector.getService(TourDAO.class);
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
