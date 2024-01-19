package com.pweb.dulich.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pweb.dulich.service.AdminService;
import com.pweb.dulich.service.TourService;
import com.pweb.dulich.service.ImageService;
import com.pweb.dulich.dto.TourIndex;
import com.pweb.dulich.model.Admin;
import com.pweb.dulich.model.Image;
import com.pweb.dulich.model.Tour;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Base64;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private TourService tourService;
    private ImageService imageService;

    public AdminController(AdminService adminService, TourService tourService, ImageService imageService) {
        this.adminService = adminService;
        this.tourService = tourService;
        this.imageService = imageService;
    }
    @ModelAttribute("admin")
    public Admin admin() {
        return new Admin();
    }
    @ModelAttribute("tour")
    public Tour tour() {
        return new Tour();
    }
    @ModelAttribute("image")
    public Image image() {
        return new Image();
    }
    @ModelAttribute("TourIndex")
    public TourIndex tourIndex() {
        return new TourIndex();
    }



    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }
    @PostMapping("login")
    public String login(@ModelAttribute("admin") Admin admin, Model model) {
        //kiểm tra xem có admin trong database không
        Admin adminLogin = adminService.getAdminLogin(admin.getUsername(), admin.getPassword());
        //nếu có thì cho gọi đến getmapping admin/homepageAdmin
        if (adminLogin != null) {
            return "redirect:/admin/homepageAdmin";
        } else {
            model.addAttribute("message", "Đăng nhập thất bại");
            return "admin/login";
        }

        
    }

    @GetMapping("/homepageAdmin")
    public String homePageAdmin(Model model) {
        //lấy 5 tour có id lớn nhất
        List<Tour> listTour = tourService.getfiveTourNearist();
        //tour có thuộc tính private List<Image> images, lấy ra ảnh có imageNumber = 1
        List<TourIndex> listTourIndex = new ArrayList<>();
        for (Tour tour : listTour) {
            Image image = imageService.findByTourIdAndImageNumber(tour.getId(), 1);
            //chuyển thành base64
            String imagebase64 = Base64.getEncoder().encodeToString(image.getImageData());
            TourIndex tourIndex = new TourIndex();
            tourIndex.setId(tour.getId());
            tourIndex.setTourName(tour.getTourName());
            //set ảnh
            tourIndex.setImage1(imagebase64);
            listTourIndex.add(tourIndex);
        }

        //truyền
        model.addAttribute("listTourIndex", listTourIndex);
        return "admin/index";
    }
    
}
