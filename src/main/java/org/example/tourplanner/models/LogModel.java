package org.example.tourplanner.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogModel {
    // daten
    private StringProperty dateTime = new SimpleStringProperty();
    private StringProperty comment = new SimpleStringProperty();
    private IntegerProperty difficulty = new SimpleIntegerProperty();
    private FloatProperty totalDistance = new SimpleFloatProperty();
    private StringProperty totalTime = new SimpleStringProperty();
    private IntegerProperty rating = new SimpleIntegerProperty();


    public LogModel() {}

    public LogModel(String dateTime, String comment, int difficulty, float totalDistance, String totalTime, int rating) {
        this.dateTime.set(dateTime);
        this.comment.set(comment);
        this.difficulty.set(difficulty);
        this.totalDistance.set(totalDistance);
        this.totalTime.set(totalTime);
        this.rating.set(rating);
    }

}
