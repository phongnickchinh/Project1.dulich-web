package com.pweb.dulich.service;

import com.pweb.dulich.model.StartDateTour;
import java.util.Date;

public interface StartDateTourService {

    public StartDateTour getStartDateTourByTourIdAndStartDate(Long tourId, Date startDate);
    public void createStartDateTour(StartDateTour startDateTour);
    public void deleteStartDateTour(StartDateTour startDateTour);
    
}
