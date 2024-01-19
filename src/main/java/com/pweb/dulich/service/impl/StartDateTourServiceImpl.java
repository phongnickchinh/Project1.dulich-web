package com.pweb.dulich.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.pweb.dulich.service.StartDateTourService;
import com.pweb.dulich.model.StartDateTour;
import com.pweb.dulich.repository.StartDateTourRepository;

@Service
public class StartDateTourServiceImpl implements StartDateTourService{

    private StartDateTourRepository startDateTourRepository;

    public StartDateTourServiceImpl(StartDateTourRepository startDateTourRepository) {
        this.startDateTourRepository = startDateTourRepository;
    }

    @Override
    public StartDateTour getStartDateTourByTourIdAndStartDate(Long tourId, Date startDate) {
        return startDateTourRepository.getStartDateTourByTourIdAndStartDate(tourId, startDate);
    }
    
    @Override
    public void createStartDateTour(StartDateTour startDateTour) {
        startDateTourRepository.save(startDateTour);
    }

    @Override
    public void deleteStartDateTour(StartDateTour startDateTour) {
        startDateTourRepository.delete(startDateTour);
    }
}
