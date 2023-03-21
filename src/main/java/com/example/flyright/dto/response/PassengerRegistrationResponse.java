package com.example.flyright.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PassengerRegistrationResponse {
    private String message;
    private HttpStatus statusCode;
    private String token;
}
