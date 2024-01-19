package com.pweb.dulich.repository;

import com.pweb.dulich.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ImageRepository  extends JpaRepository<Image, Long>{
    List<Image> findByTourId(Long tourId);
    Image findByTourIdAndImageNumber(Long tourId, int imageNumber);
    
}
