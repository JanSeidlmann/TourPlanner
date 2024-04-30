package org.example.tourplanner.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourModel {
    private String name;
    private String tourDescription;
    private String from;
    private String to;
    private TransportType transportType;
    private float distance;
    private String time;
    private String routeInformation;


    enum TransportType {
        CAR,
        BIKE,
        WALK,

    }

    private final ObservableList<String> tourList;

    public TourModel() {
        tourList = FXCollections.observableArrayList();
    }

    public ObservableList<String> getTourList() {
        return tourList;
    }

    public void addTour(String person) {
        tourList.add(person);
    }

/*    public TourModel(String name, String tourDescription, String from, String to, String transportType, float distance, String time, String routeInformation) {
        this.name = name;
        this.tourDescription = tourDescription;
        this.from = from;
        this.to = to;
        this.transportType = TransportType.valueOf(transportType);
        this.distance = distance;
        this.time = time;
        this.routeInformation = routeInformation;
    }*/

}
