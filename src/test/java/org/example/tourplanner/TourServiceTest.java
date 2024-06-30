package org.example.tourplanner;

import org.example.tourplanner.BL.TourService;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.DAL.repositories.TourDAO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
public class TourServiceTest {
    @Mock
    private TourDAO tourDAO;

    @InjectMocks
    private TourService tourService;

    @Captor
    private ArgumentCaptor<TourModel> tourCaptor;

    @Test
    void testAddTour() {
        // Arrange
        TourModel tourToAdd = new TourModel(
                "Mountain Hike",
                "Enjoy a scenic hike through the mountains",
                "Mountain Base",
                "Summit",
                "Hiking",
                10.5f,
                "4 hours",
                "Scenic route with breathtaking views");

        // Act
        tourService.addTour(tourToAdd);

        // Assert
        verify(tourDAO).save(tourCaptor.capture());
        assertEquals(tourToAdd, tourCaptor.getValue());
    }


    @Test
    void testGetAllTours() {
        // Arrange
        List<TourModel> expectedTours = Arrays.asList(
                new TourModel(
                        "Mountain Hike",
                        "Enjoy a scenic hike through the mountains",
                        "Mountain Base",
                        "Summit",
                        "Hiking",
                        10.5f,
                        "4 hours",
                        "Scenic route with breathtaking views"
                ),
                new TourModel(
                        "City Bike Tour",
                        "Explore the city on two wheels",
                        "City Center",
                        "City Outskirts",
                        "Biking",
                        25.2f,
                        "2 hours",
                        "Discover historical landmarks and local culture"
                )
        );
        Mockito.when(tourDAO.findAll()).thenReturn(expectedTours);

        // Act
        List<TourModel> actualTours = tourService.getAllTours();

        // Assert
        assertEquals(expectedTours.size(), actualTours.size());
    }

}
