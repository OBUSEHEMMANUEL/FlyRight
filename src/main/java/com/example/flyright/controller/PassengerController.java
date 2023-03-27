package com.example.flyright.controller;

import com.example.flyright.dto.request.ConfirmTokenRequest;
import com.example.flyright.dto.request.LoginRequest;
import com.example.flyright.dto.request.PassengerRegistrationRequest;
import com.example.flyright.dto.request.PaymentRequest;
import com.example.flyright.exceptionHandler.ApiResponse;
import com.example.flyright.service.PassengerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.ZonedDateTime;
@RestController
@CrossOrigin("*")
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @PostMapping("/api/v1/passenger/register")
    public ResponseEntity<ApiResponse> register(@RequestBody PassengerRegistrationRequest request, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(passengerService.register(request))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/v1/passenger/confirmPassword")
    public ResponseEntity<ApiResponse> confirmPassword(@RequestBody ConfirmTokenRequest confirmationToken, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(passengerService.confirmToken(confirmationToken))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @PostMapping("/api/v1/passenger/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws IOException {
        ApiResponse response = ApiResponse.builder()
                .data(passengerService.login(loginRequest))
                .statusCode(HttpStatus.OK.value())
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

}
