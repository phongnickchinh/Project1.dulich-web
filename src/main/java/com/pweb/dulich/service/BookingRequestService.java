package com.pweb.dulich.service;

import com.pweb.dulich.model.BookingRequest;
import java.util.List;

public interface BookingRequestService {

    public Boolean createBookingRequest(BookingRequest bookingRequest);
    public List<BookingRequest> getAllBookingRequest();

    
}
