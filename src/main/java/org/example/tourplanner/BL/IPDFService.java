package org.example.tourplanner.BL;

import org.example.tourplanner.BL.models.TourModel;

public interface IPDFService {
    void createUserListPDF(String filePath, TourModel tour) throws Exception;

}
