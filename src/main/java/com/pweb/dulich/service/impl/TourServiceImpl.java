package com.pweb.dulich.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pweb.dulich.model.Tour;
import com.pweb.dulich.repository.StartDateTourRepository;
import com.pweb.dulich.repository.TourRepository;
import com.pweb.dulich.service.TourService;
import com.pweb.dulich.model.StartDateTour;

@Service
public class TourServiceImpl implements TourService {
    private TourRepository tourRepository;
    private StartDateTourRepository startDateTour;
    
    public TourServiceImpl(TourRepository tourRepository, StartDateTourRepository startDateTour) {
        this.tourRepository = tourRepository;
        this.startDateTour = startDateTour;
    }
    @Override
    public Boolean createTour(Tour tour) {
        //nếu tour có tên đã tồn tại thì không cho tạo
        if (tourRepository.findByTourName(tour.getTourName()) != null) {
            return false;
        } else {
            tourRepository.save(tour);
            return true;
        }
    }

    @Override
    public List<Tour> getfiveTourNearist() {
        return tourRepository.findTop5ByOrderByIdDesc();
    }

    @Override
    public Tour getTourById(Long id) {
        return tourRepository.findById(id).get();
    }

    @Override
    public Tour getTourByTourName(String tourName) {
        return tourRepository.findByTourName(tourName);
    }

    @Override
    public List<StartDateTour> getStartDateTourByTourId(Long id) {
        List<StartDateTour> listStartDateTour = startDateTour.findByTourIdAndStartDate(id);
        return listStartDateTour;
    }

    @Override
    public List<Tour> getAllTour() {
        return tourRepository.findAll();
    }

    @Override
    public void editTour(Tour tour) {
        //cập nhập thông tin mới cho tour
        tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Long id) {
        //xóa tour
        tourRepository.deleteById(id);
    }
}
