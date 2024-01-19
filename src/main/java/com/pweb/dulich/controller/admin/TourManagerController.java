package com.pweb.dulich.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import com.pweb.dulich.dto.Image4Dto;
import com.pweb.dulich.dto.ImageWithId;
import com.pweb.dulich.dto.TourIndex;
import com.pweb.dulich.dto.TourWithStartDateDto;
import com.pweb.dulich.model.Tour;
import com.pweb.dulich.model.Image;
import com.pweb.dulich.model.StartDateTour;

import com.pweb.dulich.service.ImageService;
import com.pweb.dulich.service.StartDateTourService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.pweb.dulich.service.TourService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;


@Controller
@RequestMapping("/admin")
public class TourManagerController {
    
    private TourService tourService;
    private ImageService imageService;
    private StartDateTourService startDateTourService;
    public TourManagerController(TourService tourService, ImageService imageService, StartDateTourService startDateTourService) {
        this.imageService = imageService;
        this.tourService = tourService;
        this.startDateTourService = startDateTourService;
    }
    @ModelAttribute("tour")
    public Tour tour() {
        return new Tour();
    }

    @ModelAttribute("touEdit")
    public TourWithStartDateDto tourWithStartDateDto() {
        return new TourWithStartDateDto();
    }

    @ModelAttribute("image4Dto")
    public Image4Dto image4Dto() {
        return new Image4Dto();
    }


    @GetMapping("/manager")
    public String tourManagerView( Model model) {
        //lấy tất cả tour
        List<Tour> listTour = tourService.getAllTour();
        List<TourIndex> listTourIndexs = new ArrayList<>();
        //với mỗi tour lấy tất cả ảnh
        for (Tour tour : listTour) {
            List<Image> listImage = imageService.findAllByTourId(tour.getId());
            TourIndex tourIndex = new TourIndex();
            tourIndex.setId(tour.getId());
            tourIndex.setTourName(tour.getTourName());
            tourIndex.setPrice(tour.getPrice());
            tourIndex.setDescription(tour.getDescription());
            tourIndex.setLength(tour.getLength());
            tourIndex.setSchedule(tour.getSchedule());
            tourIndex.setDetailSchedule(tour.getDetailSchedule());
            tourIndex.setNote (tour.getNote());
            //thời gian chuyển thành String dd/MM/yyyy cách nhau bởi dấu phẩy
            List<StartDateTour> listStartDateTour = tourService.getStartDateTourByTourId(tour.getId());
            String listStartDate = "";
            for (StartDateTour startDateTour : listStartDateTour) {
                String date = new SimpleDateFormat("dd/MM/yyyy").format(startDateTour.getStartDate());
                listStartDate += date + ",";
            }
            tourIndex.setListStartDate(listStartDate);
            //chuyển ảnh thành base64 và set vào tourIndex
            for (Image image : listImage) {
                //kiểm tra null
                if (image.getImageData() != null) {
                    String imagebase64 = Base64.getEncoder().encodeToString(image.getImageData());
                    if (image.getImageNumber() == 1) {
                        tourIndex.setImage1(imagebase64);
                    } else if (image.getImageNumber() == 2) {
                        tourIndex.setImage2(imagebase64);
                    } else if (image.getImageNumber() == 3) {
                        tourIndex.setImage3(imagebase64);
                    } else if (image.getImageNumber() == 4) {
                        tourIndex.setImage4(imagebase64);
                    }
                }
            }
            listTourIndexs.add(tourIndex);
        }

        model.addAttribute("listTour", listTourIndexs);
        return "admin/tourManager";
    }

    @GetMapping("/tourInfor")
    public String tourDetailView(@RequestParam("id") Long id, Model model) {
        Tour tour = tourService.getTourById(id);
        //lấy các ngày đi của tour
        List<StartDateTour> listStartDateTour = tourService.getStartDateTourByTourId(id);
        //lấy các ảnh của tour
        List<Image> listImage = imageService.findAllByTourId(id);
        List<ImageWithId> listImageBase64 = new ArrayList<>();
        for (Image image : listImage) {

            Long imageId = image.getId();
            String imagebase64 = java.util.Base64.getEncoder().encodeToString(image.getImageData());
            ImageWithId imageWithId = new ImageWithId(imageId, imagebase64);
            listImageBase64.add(imageWithId);
        }
        
        model.addAttribute("tour", tour);
        model.addAttribute("startDateTour", listStartDateTour);
        model.addAttribute("listImage", listImageBase64);

        return "admin/tourInfor";
    }

    @PostMapping("/manager/create")
    public String createTour(@ModelAttribute("tourEdit") TourWithStartDateDto tourEdit,  Model model) {
        //in ra thông tin tour bao gồm tên thộc tính và giá trị
        System.out.println(tourEdit.toString());

        //truyền tourEdit vào  tour
        Tour tour = new Tour();
        tour.setTourName(tourEdit.getTourName());
        tour.setSchedule(tourEdit.getSchedule());
        tour.setLength(tourEdit.getLength());
        tour.setPrice(tourEdit.getPrice());
        tour.setDescription(tourEdit.getDescription());
        tour.setDetailSchedule(tourEdit.getDetailSchedule());
        tour.setNote(tourEdit.getNote());

        tourService.createTour(tour);
        Tour tourCreated = tourService.getTourByTourName(tourEdit.getTourName());
        

        //trước tiền cắt chuỗi ngày tháng năm ngăn cách bởi dấu phẩy
        String[] startDate = tourEdit.getListStartDate().split(",");
        //đối với mỗi chuỗi dd/MM/yyyy chuyển thành kiểu Date và tạo startDateTour với tourCreated
        for (String date : startDate) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                StartDateTour startDateTour = new StartDateTour();
                startDateTour.setStartDate(date1);
                startDateTour.setTour(tourCreated);
                startDateTourService.createStartDateTour(startDateTour);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //ảnh được truyền tới controller dưới dạng multipartfile
        //lưu ảnh vào database dạng text base64
        
        //lưu ảnh 1
        Image image1 = new Image();
        image1.setImageName(tourEdit.getImage1().getOriginalFilename());
        image1.setImageNumber(1);
        byte[] imageData1 = null;
        try {
            imageData1 = tourEdit.getImage1().getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        image1.setImageData(imageData1);
        image1.setTour(tourCreated);
        imageService.createImage(image1);

        //thực hiện với 2 3 4
        Image image2 = new Image();
        image2.setImageName(tourEdit.getImage2().getOriginalFilename());
        image2.setImageNumber(2);
        byte[] imageData2 = null;
        try {
            imageData2 = tourEdit.getImage2().getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        image2.setImageData(imageData2);
        image2.setTour(tourCreated);
        imageService.createImage(image2);

        Image image3 = new Image();
        image3.setImageName(tourEdit.getImage3().getOriginalFilename());
        image3.setImageNumber(3);
        byte[] imageData3 = null;
        try {
            imageData3 = tourEdit.getImage3().getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        image3.setImageData(imageData3);
        image3.setTour(tourCreated);
        imageService.createImage(image3);

        Image image4 = new Image();
        image4.setImageName(tourEdit.getImage4().getOriginalFilename());
        image4.setImageNumber(4);
        byte[] imageData4 = null;
        try {
            imageData4 = tourEdit.getImage4().getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        image4.setImageData(imageData4);
        image4.setTour(tourCreated);
        imageService.createImage(image4);

        //trả về trang trắng có chữ lấy thống tin thành công
        model.addAttribute("message", "Tạo tour thành công");
        return "redirect:/admin/manager";

    }

    //chức năng sửa tour
    @PostMapping("/manager/edit/{id}")
    public String editTour( @RequestParam("id") String id, @ModelAttribute("tourEditFix") TourWithStartDateDto tourEdit, Model model) {
        //tạo đối tượng hiển thị thông tin hiện tại
        //chuyển id từ String sang Long
        Long idLong = Long.parseLong(id);
        Tour tour = tourService.getTourById(idLong);
        //lấy startDateTour theo tourId và xoá chúng đi
        List<StartDateTour> listStartDateTour = tourService.getStartDateTourByTourId(idLong);
        for (StartDateTour startDateTour : listStartDateTour) {
            startDateTourService.deleteStartDateTour(startDateTour);
        }
        //lấy chuỗi ngày mưới từ tourEdit
        String[] startDate = tourEdit.getListStartDate().split(",");
        //đối với mỗi chuỗi dd/MM/yyyy chuyển thành kiểu Date và tạo startDateTour với tourCreated
        for (String date : startDate) {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                StartDateTour startDateTour = new StartDateTour();
                startDateTour.setStartDate(date1);
                startDateTour.setTour(tour);
                startDateTourService.createStartDateTour(startDateTour);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //ảnh giữ nguyên
        //lưu các thông tin mới vào tour
        tour.setTourName(tourEdit.getTourName());
        tour.setSchedule(tourEdit.getSchedule());
        tour.setLength(tourEdit.getLength());
        tour.setPrice(tourEdit.getPrice());
        tour.setDescription(tourEdit.getDescription());
        tour.setDetailSchedule(tourEdit.getDetailSchedule());
        tour.setNote(tourEdit.getNote());
        tourService.editTour(tour);

        
        model.addAttribute("message", "Sửa tour thành công");
        return "redirect:/admin/manager";
    }

    //chức năng xóa tour
    @PostMapping("/manager/delete")
    public String deleteTour(@RequestParam("id") Long id, Model model) {
        //xóa tour
        tourService.deleteTour(id);

        return "redirect:/admin/manager";
    }
}
