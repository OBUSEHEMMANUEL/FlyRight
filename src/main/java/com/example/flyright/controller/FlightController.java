package com.example.flyright.controller;

import com.example.flyright.dto.request.FlightSearchRequest;
import com.example.flyright.dto.response.FlightSearchResponse;
import com.example.flyright.exceptionHandler.ApiResponse;
import com.example.flyright.service.airline.FlightSchedule;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZonedDateTime;

@RestController
@CrossOrigin("*")

public class FlightController {
    @Autowired
    private FlightSchedule flightSchedule;

    @PostMapping("/api/v1/get-flights")
    public ResponseEntity<ApiResponse> searchFlights(@RequestBody FlightSearchRequest request, HttpServletRequest servletRequest) throws IOException {
        FlightSearchResponse searchResponse = flightSchedule.getFlightSchedule(request);
        ApiResponse response = ApiResponse.builder()
                .data(searchResponse)
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(servletRequest.getRequestURI())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
