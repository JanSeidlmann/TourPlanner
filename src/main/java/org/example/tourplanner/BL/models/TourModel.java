package org.example.tourplanner.BL.models;

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
    private StringProperty nameProperty;
    @Transient
    private StringProperty tourDescriptionProperty;
    @Transient
    private StringProperty fromProperty;
    @Transient
    private StringProperty toProperty;
    @Transient
    private StringProperty transportTypeProperty;
    @Transient
    private FloatProperty distanceProperty;
    @Transient
    private StringProperty timeProperty;
    @Transient
    private StringProperty routeInformationProperty;
    @Transient
    private ObservableList<LogModel> logs;

    public TourModel() {
        initProperties();
    }

    public TourModel(String name, String description, String from, String to, String transportType, Float distance, String time, String routeInformation) {
        this.name = name;
        this.tourDescription = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.time = time;
        this.routeInformation = routeInformation;
        initProperties();
    }

    private void initProperties() {
        this.nameProperty = new SimpleStringProperty(this.name);
        this.tourDescriptionProperty = new SimpleStringProperty(this.tourDescription);
        this.fromProperty = new SimpleStringProperty(this.from);
        this.toProperty = new SimpleStringProperty(this.to);
        this.transportTypeProperty = new SimpleStringProperty(this.transportType);
        this.distanceProperty = new SimpleFloatProperty(this.distance != null ? this.distance : 0.0f);
        this.timeProperty = new SimpleStringProperty(this.time);
        this.routeInformationProperty = new SimpleStringProperty(this.routeInformation);
        this.logs = FXCollections.observableArrayList();
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
