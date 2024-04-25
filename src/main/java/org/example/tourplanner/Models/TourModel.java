package org.example.tourplanner.Models;

public class TourModel {
    // name, tour description, from, to, transport type, tour distance,
    //estimated time, route information
    private final String name;
    private final String tourDescription;
    private final String from;
    private final String to;

    // maybe make into an enum
    private final String transportType;
    private final float distance;
    private final String time;
    private final String routeInformation;

    public TourModel(String name, String tourDescription, String from, String to, String transportType, float distance, String time, String routeInformation) {
        this.name = name;
        this.tourDescription = tourDescription;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.time = time;
        this.routeInformation = routeInformation;
    }



}
