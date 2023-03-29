package com.example.flyright.controller;

import com.example.flyright.dto.request.BookingRequest;
import com.example.flyright.exceptionHandler.ApiResponse;
import com.example.flyright.service.BookTicketService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class BookTicketController {
    @Autowired
    BookTicketService bookTicketService;
    @PostMapping("/api/v1/bookings")
    public ResponseEntity<ApiResponse> bookFlight(BookingRequest request, HttpServletRequest httpServletRequest){

        ApiResponse apiResponse =  ApiResponse.builder()
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true).
                build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
