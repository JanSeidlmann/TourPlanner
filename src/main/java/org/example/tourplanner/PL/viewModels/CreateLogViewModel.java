package org.example.tourplanner.PL.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.extern.java.Log;
import org.example.tourplanner.BL.ILogService;
import org.example.tourplanner.BL.LogService;
import org.example.tourplanner.BL.TourService;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

public class CreateLogViewModel implements Injectable {
    private final ILogService logService;

    @Getter
    private ObservableList<LogModel> logTableList;

    public CreateLogViewModel(ILogService logService) {
        this.logService = DefaultInjector.getService(LogService.class);
        this.logTableList = FXCollections.observableArrayList();
        loadLogs();
    }

    private void loadLogs() {
        logTableList.clear();
        var validLogModels = logService.getAllLogs();
        logTableList.addAll(validLogModels);
    }

    private void addLog(LogModel log) {
        logService.addLog(log);
        loadLogs();
    }

    private void deleteLog(LogModel log) {
        logService.deleteLog(log);
        loadLogs();
    }
}
