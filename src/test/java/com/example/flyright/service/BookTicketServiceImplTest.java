package com.example.flyright.service;

import com.example.flyright.dto.request.BookingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookTicketServiceImplTest {
    @Autowired
    BookTicketService bookTicketService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void bookFlight() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setNumSeatsBooked(12);
        bookingRequest.setEmailAddress("obusehemmanuel208@gmail.com");
        bookingRequest.setPassengerName("derek");

        assertEquals(HttpStatus.OK,bookTicketService.bookFlight(bookingRequest).getStatus());
    }
}