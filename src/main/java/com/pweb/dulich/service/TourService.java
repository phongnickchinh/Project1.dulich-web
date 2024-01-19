package com.pweb.dulich.service;
import java.util.List;

import com.pweb.dulich.model.StartDateTour;
import com.pweb.dulich.model.Tour;

public interface TourService  {
    public Boolean createTour(Tour tour);
    public List<Tour> getfiveTourNearist();
    public List<Tour> getAllTour();
    public Tour getTourById(Long id);
    public List<StartDateTour> getStartDateTourByTourId(Long id);
    public void editTour(Tour tour);
    public Tour getTourByTourName(String tourName);
    public void deleteTour(Long id);
    
}
