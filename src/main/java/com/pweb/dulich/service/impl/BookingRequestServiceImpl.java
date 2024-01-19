package com.pweb.dulich.service.impl;

import org.springframework.stereotype.Service;
import com.pweb.dulich.model.BookingRequest;
import com.pweb.dulich.repository.BookingRequestRepository;
import com.pweb.dulich.service.BookingRequestService;

import java.util.Date;
import java.util.List;

@Service
public class BookingRequestServiceImpl implements BookingRequestService{
    
    private BookingRequestRepository bookingRequestRepository;

    public BookingRequestServiceImpl(BookingRequestRepository bookingRequestRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
    }

    @Override
    public Boolean createBookingRequest(BookingRequest bookingRequest) {
        bookingRequest.setRequestTime(new Date());
        bookingRequest.setStatus(0);
        bookingRequestRepository.save(bookingRequest);
        return true;
    }

    @Override
    public List<BookingRequest> getAllBookingRequest() {
        return bookingRequestRepository.findAll();
    }
}
