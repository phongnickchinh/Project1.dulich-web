package com.pweb.dulich.controller.guest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pweb.dulich.model.Tour;
import com.pweb.dulich.service.TourService;
import com.pweb.dulich.dto.AdviceRequestDto;
import com.pweb.dulich.dto.TourWithStartDateDto;
import com.pweb.dulich.dto.BookingRequestDto;
import com.pweb.dulich.dto.ImageWithId;
import com.pweb.dulich.dto.TourIndex;
import com.pweb.dulich.model.AdviceRequest;
import com.pweb.dulich.model.BookingRequest;
import com.pweb.dulich.service.AdviceRequestService;
import com.pweb.dulich.service.BookingRequestService;
import com.pweb.dulich.service.StartDateTourService;
import com.pweb.dulich.model.Image;
import com.pweb.dulich.service.ImageService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.pweb.dulich.model.StartDateTour;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/guest")
public class TourController {
    
    private TourService tourService;
    private AdviceRequestService adviceRequestService;
    private BookingRequestService bookingRequestService;
    private StartDateTourService startDateTourService;
    private ImageService imageService;

    
    public TourController(
                            TourService tourService,
                            AdviceRequestService adviceRequestService,
                            BookingRequestService bookingRequestService,
                            StartDateTourService startDateTourService,
                            ImageService imageService) {
        this.tourService = tourService;
        this.adviceRequestService = adviceRequestService;
        this.bookingRequestService = bookingRequestService;
        this.startDateTourService = startDateTourService;
        this.imageService = imageService;
    }

    @ModelAttribute("tourWithStartDate")
    public TourWithStartDateDto tourWithStartDate() {
        return new TourWithStartDateDto();
    }
    @ModelAttribute("tour")
    public Tour tour() {
        return new Tour();
    }

    @ModelAttribute("adviceRequestDto")
    public AdviceRequestDto adviceRequestDto() {
        return new AdviceRequestDto();
    }

    @ModelAttribute("bookingRequest")
    public BookingRequest bookingRequest() {
        return new BookingRequest();
    }

    @ModelAttribute("bookingRequestDto")
    public BookingRequestDto bookingRequestDto() {
        return new BookingRequestDto();
    }

    @GetMapping("/tour")
    public String tourView(Model model) {
        List<Tour> listTour = tourService.getAllTour();
        //đối với mỗi tour lấy ngày đi
        List<TourIndex> listTourIndex = new ArrayList<>();
        for (Tour tour : listTour) {
            //lấy ra hinh ảnh đầu tiên
            Image image = imageService.findByTourIdAndImageNumber(tour.getId(), 1);
            //chuyển thành base64
            //kiểm tra null
            TourIndex tourIndex = new TourIndex();
            tourIndex.setId(tour.getId());
            tourIndex.setTourName(tour.getTourName());
            tourIndex.setPrice(tour.getPrice());
            tourIndex.setDescription(tour.getDescription());
            tourIndex.setLength(tour.getLength());
            tourIndex.setSchedule(tour.getSchedule());
            //set ảnh
            if(image !=null){
                String imagebase64 = Base64.getEncoder().encodeToString(image.getImageData());
                tourIndex.setImage1(imagebase64);
            }
            listTourIndex.add(tourIndex);
        }

        model.addAttribute("listTour", listTourIndex);
        return "guest/tour";
    }

    @GetMapping("/tourInfor")
    public String tourDetailView(@RequestParam("id") Long id, Model model) {
        Tour tour = tourService.getTourById(id);
        //lấy các ngày đi của tour
        List<StartDateTour> listStartDateTour = tourService.getStartDateTourByTourId(id);
        //lấy các ảnh của tour
        List<Image> listImage = imageService.findAllByTourId(id);
        //chuyeern ảnh thành base6
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

        return "guest/tourInfor";
    }

    @PostMapping("/adviceRequest")
    public String sentAdviceRequest(@ModelAttribute("adviceRequestDto") AdviceRequestDto adviceRequestDto, Model model) {
        //lấy thông tin tour theo id
        Long tourId = Long.parseLong(adviceRequestDto.getTourId());
        Tour tour = tourService.getTourById(tourId);
        //tạo adviceRequest
        AdviceRequest adviceRequest = new AdviceRequest();
        adviceRequest.setCustomerName(adviceRequestDto.getCustomerName());
        adviceRequest.setPhoneNumber(adviceRequestDto.getPhoneNumber());
        adviceRequest.setTour(tour);
        //gọi hàm tạo yêu cầu tư vấn
        adviceRequestService.createAdviceRequest(adviceRequest);
        return "redirect:/guest/tourInfor?id="+adviceRequestDto.getTourId();
    }

    @PostMapping("/bookingRequest")
    public String sentBookingRequest(@ModelAttribute("bookingRequestDto") BookingRequestDto bookingRequestDto, Model model) {
        //lấy thông tin tour theo id
        Long tourId = Long.parseLong(bookingRequestDto.getTourId());
        //chuyển đổi startDate từ String yyyy-mm-dd sang Date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date startDate = null;
        try {
            startDate = inputFormat.parse(bookingRequestDto.getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //lấy startDateTour theo tourId và startDate
        StartDateTour startDateTour = startDateTourService.getStartDateTourByTourIdAndStartDate(tourId, startDate);

        //tạo bookingRequest
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setCustomerName(bookingRequestDto.getCustomerName());
        bookingRequest.setPhoneNumber(bookingRequestDto.getPhoneNumber());
        bookingRequest.setEmail(bookingRequestDto.getEmail());
        bookingRequest.setAddress(bookingRequestDto.getAddress());
        bookingRequest.setOver11yr(bookingRequestDto.getOver11yr());
        bookingRequest.setFrom6to11yr(bookingRequestDto.getFrom6to11yr());
        bookingRequest.setUnder6yr(bookingRequestDto.getUnder6yr());
        bookingRequest.setNote(bookingRequestDto.getNote());
        bookingRequest.setStartDateTour(startDateTour);

        model.addAttribute("message", "Gửi yêu cầu đặt tour thành công");
        bookingRequestService.createBookingRequest(bookingRequest);
        return "redirect:/guest/tourInfor?id="+ tourId;
    }

    @GetMapping("/tour/searchTour")
    public String searchTour(@RequestParam("scheduleSearch") String tourName, Model model) {
        //tìm kiếm tour có tên chứa touName
        List<Tour> listTour = tourService.searchTour(tourName);
        List<TourIndex> listTourIndex = new ArrayList<>();
        List<Tour> fullTour = tourService.getAllTour();
        //với mỗi tour lấy ra hinh ảnh đầu tiên
        for (Tour tour : listTour) {
            Image image = imageService.findByTourIdAndImageNumber(tour.getId(), 1);
            //chuyển thành base64
            //kiểm tra null
            
            TourIndex tourIndex = new TourIndex();
            tourIndex.setId(tour.getId());
            tourIndex.setTourName(tour.getTourName());
            tourIndex.setPrice(tour.getPrice());
            tourIndex.setDescription(tour.getDescription());
            tourIndex.setLength(tour.getLength());
            tourIndex.setSchedule(tour.getSchedule());
            //set ảnh
            if(image !=null){
                String imagebase64 = Base64.getEncoder().encodeToString(image.getImageData());
                tourIndex.setImage1(imagebase64);
            }
            listTourIndex.add(tourIndex);
        }
        List<String> scheduleList = new ArrayList<>();
        for (Tour tour : fullTour) {
            //kiểm tra null và kiểm tra rỗng
            if (tour.getSchedule() != null && !tour.getSchedule().isEmpty()) {
                scheduleList.add(tour.getSchedule());
            }
        }
        model.addAttribute("listTour", listTourIndex);
        model.addAttribute("tourNameList", scheduleList);
        return "guest/tour";
    }
    
}
