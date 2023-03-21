package com.example.flyright.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class LoginResponse {
    private  String message;
    private HttpStatus statusCode;
}
