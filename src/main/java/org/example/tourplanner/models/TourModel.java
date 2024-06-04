package org.example.tourplanner.models;

import javax.persistence.*;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Data
@Entity
@Table(name = "tourmodels")
public class TourModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "tourDescription")
    private String tourDescription;
    @Column(name = "`from`")
    private String from;
    @Column(name = "`to`")
    private String to;
    @Column(name = "transportType")
    private String transportType;
    @Column(name = "distance")
    private Float distance;
    @Column(name = "time")
    private String time;
    @Column(name = "routeInformation")
    private String routeInformation;

    @Transient
    private StringProperty nameProperty = new SimpleStringProperty();
    @Transient
    private StringProperty tourDescriptionProperty = new SimpleStringProperty();
    @Transient
    private StringProperty fromProperty = new SimpleStringProperty();
    @Transient
    private StringProperty toProperty = new SimpleStringProperty();
    @Transient
    private StringProperty transportTypeProperty = new SimpleStringProperty();
    @Transient
    private FloatProperty distanceProperty = new SimpleFloatProperty();
    @Transient
    private StringProperty timeProperty = new SimpleStringProperty();
    @Transient
    private StringProperty routeInformationProperty = new SimpleStringProperty();
    @Transient
    private ObservableList<LogModel> logs = FXCollections.observableArrayList();

    public TourModel() {}

    public TourModel(String name, String description, String from, String to, String transportType, Float distance, String time, String routeInformation) {
        setName(name);
        setTourDescription(description);
        setFrom(from);
        setTo(to);
        setTransportType(transportType);
        setDistance(distance);
        setTime(time);
        setRouteInformation(routeInformation);
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void syncToProperties() {
        this.nameProperty.set(this.name);
        this.tourDescriptionProperty.set(this.tourDescription);
        this.fromProperty.set(this.from);
        this.toProperty.set(this.to);
        this.transportTypeProperty.set(this.transportType);
        this.distanceProperty.set(this.distance);
        this.timeProperty.set(this.time);
        this.routeInformationProperty.set(this.routeInformation);
    }

    @PrePersist
    @PreUpdate
    private void syncFromProperties() {
        this.name = this.nameProperty.get();
        this.tourDescription = this.tourDescriptionProperty.get();
        this.from = this.fromProperty.get();
        this.to = this.toProperty.get();
        this.transportType = this.transportTypeProperty.get();
        this.distance = this.distanceProperty.get();
        this.time = this.timeProperty.get();
        this.routeInformation = this.routeInformationProperty.get();
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
