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
       bookingRequest.setEmailAddress("derek@gmail.com");
       bookingRequest.setDob("12/13/2023");
       bookingRequest.setFirstName("derek");
       bookingRequest.setSeatNumBooked(12);
       bookingRequest.setNationality("Canada");
       bookingRequest.setMiddleName("Marcus");
       bookingRequest.setLastName("Manuel");
       bookingRequest.setTitle("MR");

        assertEquals(HttpStatus.OK,bookTicketService.bookFlight(bookingRequest).getStatus());
    }
}