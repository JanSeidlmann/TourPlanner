package org.example.tourplanner.BL;

import org.example.tourplanner.BL.models.LogModel;

import java.util.List;

public interface ILogService {
    List<LogModel> getAllLogs();
    void addLog(LogModel log);
    void deleteLog(LogModel log);


}
