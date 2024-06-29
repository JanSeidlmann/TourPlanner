package org.example.tourplanner.BL.models;

import javax.persistence.*;
import javafx.beans.property.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "logmodels")
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dateTime")
    private String dateTime;
    @Column(name = "comment")
    private String comment;
    @Column(name = "difficulty")
    private int difficulty;
    @Column(name = "totalDistance")
    private float totalDistance;
    @Column(name = "totalTime")
    private String totalTime;
    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private TourModel tour;

    @Transient
    private StringProperty dateTimeProperty;
    @Transient
    private StringProperty commentProperty;
    @Transient
    private IntegerProperty difficultyProperty;
    @Transient
    private FloatProperty totalDistanceProperty;
    @Transient
    private StringProperty totalTimeProperty;
    @Transient
    private IntegerProperty ratingProperty;

    public LogModel() {
        initProperties();
    }

    public LogModel(String dateTime, String comment, int difficulty, float totalDistance, String totalTime, int rating, TourModel tour) {
        this.dateTime = dateTime;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.rating = rating;
        this.tour = tour;
        initProperties();
    }

    private void initProperties() {
        this.dateTimeProperty = new SimpleStringProperty(this.dateTime);
        this.commentProperty = new SimpleStringProperty(this.comment);
        this.difficultyProperty = new SimpleIntegerProperty(this.difficulty);
        this.totalDistanceProperty = new SimpleFloatProperty(this.totalDistance);
        this.totalTimeProperty = new SimpleStringProperty(this.totalTime);
        this.ratingProperty = new SimpleIntegerProperty(this.rating);
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void syncToProperties() {
        this.dateTimeProperty.set(this.dateTime);
        this.commentProperty.set(this.comment);
        this.difficultyProperty.set(this.difficulty);
        this.totalDistanceProperty.set(this.totalDistance);
        this.totalTimeProperty.set(this.totalTime);
        this.ratingProperty.set(this.rating);
    }

    @PrePersist
    @PreUpdate
    private void syncFromProperties() {
        this.dateTime = this.dateTimeProperty.get();
        this.comment = this.commentProperty.get();
        this.difficulty = this.difficultyProperty.get();
        this.totalDistance = this.totalDistanceProperty.get();
        this.totalTime = this.totalTimeProperty.get();
        this.rating = this.ratingProperty.get();
    }
}