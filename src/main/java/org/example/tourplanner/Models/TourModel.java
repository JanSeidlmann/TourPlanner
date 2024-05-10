package org.example.tourplanner.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourModel {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty tourDescription = new SimpleStringProperty();
    private StringProperty from = new SimpleStringProperty();
    private StringProperty to = new SimpleStringProperty();
    private StringProperty transportType = new SimpleStringProperty();
    private FloatProperty distance = new SimpleFloatProperty();;
    private StringProperty time = new SimpleStringProperty();;
    private StringProperty routeInformation = new SimpleStringProperty();;



    // Standardkonstruktor
    public TourModel() {}

    // Konstruktor mit allen Parametern
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
}
