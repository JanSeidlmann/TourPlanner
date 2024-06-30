package org.example.tourplanner.BL;

import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.BL.models.TourModel;

import java.util.List;

public interface ILogService {
    List<LogModel> getAllLogs();
    List<LogModel> getLogs(TourModel tour);
    void addLog(LogModel log);
    void editLog(LogModel log);
    void deleteLog(LogModel log);


}
