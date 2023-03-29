package com.example.flyright.service;

import com.example.flyright.dto.request.BookingRequest;
import com.example.flyright.dto.response.BookingResponse;

public interface BookTicketService {
    BookingResponse bookFlight(BookingRequest bookingRequest);

}
