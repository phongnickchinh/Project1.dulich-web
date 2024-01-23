package com.pweb.dulich.controller.guest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pweb.dulich.dto.TourIndex;
import com.pweb.dulich.model.Image;
import com.pweb.dulich.model.Tour;
import com.pweb.dulich.service.ImageService;
import com.pweb.dulich.service.TourService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/guest")
public class HomeController {
    private TourService tourService;
    private ImageService imageService;
    public HomeController(TourService tourService, ImageService imageService) {
        this.tourService = tourService;
        this.imageService = imageService;
    }
    @ModelAttribute("tour")
    public Tour tour() {
        return new Tour();
    }


    
    @GetMapping("/homepageGuest")
    public String homePageGuest(Model model) {
        //lấy 5 tour có id lớn nhất
        List<Tour> listTour = tourService.getfiveTourNearist();
        List<Tour> fullTour = tourService.getAllTour();
        //tour có thuộc tính private List<Image> images, lấy ra ảnh có imageNumber = 1
        List<TourIndex> listTourIndex = new ArrayList<>();
        for (Tour tour : listTour) {
            Image image = imageService.findByTourIdAndImageNumber(tour.getId(), 1);
            //chuyển thành base64
            String imagebase64 = Base64.getEncoder().encodeToString(image.getImageData());
            TourIndex tourIndex = new TourIndex();
            tourIndex.setId(tour.getId());
            tourIndex.setTourName(tour.getTourName());
            tourIndex.setPrice(tour.getPrice());
            tourIndex.setDescription(tour.getDescription());
            tourIndex.setLength(tour.getLength());
            tourIndex.setSchedule(tour.getSchedule());
            //set ảnh
            tourIndex.setImage1(imagebase64);
            listTourIndex.add(tourIndex);
        }

        List<String> scheduleList = new ArrayList<>();
        for (Tour tour : fullTour) {
            //kiểm tra null và kiểm tra rỗng
            if (tour.getSchedule() != null && !tour.getSchedule().isEmpty()) {
                scheduleList.add(tour.getSchedule());
            }
        }
        //in ra scheduleList
        for (String schedule : scheduleList) {
            System.out.println(schedule);
        }
        //gửi listTourIndex lên view
        model.addAttribute("listTourIndex", listTourIndex);
        model.addAttribute("tourNameList", scheduleList);
        return "guest/index";
    }


    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "guest/aboutUs";
    }
}
