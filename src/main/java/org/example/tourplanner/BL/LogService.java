package org.example.tourplanner.BL;

import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DAL.repositories.LogDAO;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

import java.util.List;

public class LogService implements ILogService, Injectable {
    private final LogDAO logDAO;

    public LogService() {
        this.logDAO = DefaultInjector.getService(LogDAO.class);
    }

    @Override
    public List<LogModel> getAllLogs() {
        return logDAO.findALl();
    }

    @Override
    public List<LogModel> getLogs(TourModel tour) {
        return logDAO.findByTour(tour);
    }

    @Override
    public void addLog(LogModel log) {
        logDAO.save(log);
    }

    @Override
    public void editLog(LogModel log) {
        logDAO.update(log);
    }

    @Override
    public void deleteLog(LogModel log) {
        logDAO.delete(log);
    }
}
