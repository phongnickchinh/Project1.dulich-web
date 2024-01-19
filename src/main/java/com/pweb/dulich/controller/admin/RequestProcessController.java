package com.pweb.dulich.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pweb.dulich.model.AdviceRequest;
import com.pweb.dulich.model.BookingRequest;
import com.pweb.dulich.service.AdviceRequestService;
import com.pweb.dulich.service.BookingRequestService;

import org.springframework.ui.Model;
import java.util.List;





@Controller
@RequestMapping("/admin")
public class RequestProcessController {

    
    private AdviceRequestService adviceRequestService;
    private BookingRequestService bookingRequestService;

    public RequestProcessController(AdviceRequestService adviceRequestService, BookingRequestService bookingRequestService) {
        this.adviceRequestService = adviceRequestService;
        this.bookingRequestService = bookingRequestService;
    }

    @ModelAttribute("adviceRequest")
    public AdviceRequest adviceRequest() {
        return new AdviceRequest();
    }
    @ModelAttribute("bookingRequest")
    public BookingRequest bookingRequest() {
        return new BookingRequest();
    }

    @GetMapping("/requestProcess")
    public String requestProcessView(Model model) {

        List<AdviceRequest> listAdviceRequest = adviceRequestService.getAllAdviceRequest();
        List<BookingRequest> listBookingRequest = bookingRequestService.getAllBookingRequest();

        model.addAttribute("listAdviceRequest", listAdviceRequest);
        model.addAttribute("listBookingRequest", listBookingRequest);

        return "admin/requestProcess";
    }
    
    
}
