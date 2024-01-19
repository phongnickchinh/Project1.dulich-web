package com.pweb.dulich.service;

import com.pweb.dulich.model.Image;
import java.util.List;

public interface ImageService {
    List<Image> findAllByTourId(Long tourId);
    void createImage(Image image);
    Image findByTourIdAndImageNumber(Long tourId, int imageNumber);
}
