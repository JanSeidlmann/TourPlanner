package org.example.tourplanner.PL.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.tourplanner.BL.ILogService;
import org.example.tourplanner.BL.LogService;
import org.example.tourplanner.BL.models.LogModel;
import org.example.tourplanner.DefaultInjector;
import org.example.tourplanner.Injectable;

public class CreateLogViewModel implements Injectable {
    private final ILogService logService;

    @Getter
    private ObservableList<LogModel> logTableView;

    public CreateLogViewModel() {
        this.logService = DefaultInjector.getService(LogService.class);
        this.logTableView = FXCollections.observableArrayList();
        loadLogs();
    }

    private void loadLogs() {
        logTableView.clear();
        var validLogModels = logService.getAllLogs();
        logTableView.addAll(validLogModels);
    }

    public void addLog(LogModel log) {
        logService.addLog(log);
        loadLogs();
    }

    public void deleteLog(LogModel log) {
        logService.deleteLog(log);
        loadLogs();
    }
}
