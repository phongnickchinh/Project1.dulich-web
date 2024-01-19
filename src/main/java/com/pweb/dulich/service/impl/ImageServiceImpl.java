package com.pweb.dulich.service.impl;

import org.springframework.stereotype.Service;
import com.pweb.dulich.service.ImageService;
import com.pweb.dulich.repository.ImageRepository;
import com.pweb.dulich.model.Image;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    private ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAllByTourId(Long tourId) {
        return imageRepository.findByTourId(tourId);
    }

    @Override
    public void createImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image findByTourIdAndImageNumber(Long tourId, int imageNumber) {
        return imageRepository.findByTourIdAndImageNumber(tourId, imageNumber);
    }
}
