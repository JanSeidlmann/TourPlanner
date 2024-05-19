package org.example.tourplanner.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class TourModel {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty tourDescription = new SimpleStringProperty();
    private StringProperty from = new SimpleStringProperty();
    private StringProperty to = new SimpleStringProperty();
    private StringProperty transportType = new SimpleStringProperty();
    private FloatProperty distance = new SimpleFloatProperty();
    private StringProperty time = new SimpleStringProperty();
    private StringProperty routeInformation = new SimpleStringProperty();
    private ObservableList<LogModel> logs = FXCollections.observableArrayList();

    public TourModel() {}

    public TourModel(String name, String description, String from, String to, String transportType, Float distance, String time, String routeInformation) {
        this.name.set(name);
        this.tourDescription.set(description);
        this.from.set(from);
        this.to.set(to);
        this.transportType.set(transportType);
        this.distance.set(distance);
        this.time.set(time);
        this.routeInformation.set(routeInformation);
    }

    public void addLog(LogModel log) {
        this.logs.add(log);
    }

    public void removeLog(LogModel log) {
        this.logs.remove(log);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourModel tourModel = (TourModel) o;

        return Objects.equals(name, tourModel.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
