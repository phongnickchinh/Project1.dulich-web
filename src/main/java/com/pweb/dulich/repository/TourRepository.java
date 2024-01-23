package com.pweb.dulich.repository;

import com.pweb.dulich.model.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>{

    public Tour findByTourName(String tourName);

    //lấy 5 tour được thêm gần nhất, có id lớn nhất
    List<Tour> findTop5ByOrderByIdDesc();
    //lây các tour có schedule chứa chuỗi search
    List<Tour> findByScheduleContaining(String search);


}
