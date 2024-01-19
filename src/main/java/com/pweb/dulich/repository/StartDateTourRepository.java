package com.pweb.dulich.repository;

import com.pweb.dulich.model.StartDateTour;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StartDateTourRepository extends JpaRepository<StartDateTour,Long> {

    public List<StartDateTour> findByTourId(Long id);

    @Query(value = "SELECT * FROM Start_Date_Tour s WHERE s.tour_Id =:id AND s.start_Date>=NOW() ORDER BY s.start_Date DESC", nativeQuery = true)
    public List<StartDateTour> findByTourIdAndStartDate(Long id);

    public StartDateTour getStartDateTourByTourIdAndStartDate(Long tourId, java.util.Date startDate);
    
}
