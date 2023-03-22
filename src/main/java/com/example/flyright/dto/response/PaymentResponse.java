package com.example.flyright.dto.response;

import com.example.flyright.data.model.RecipientData;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PaymentResponse {
    private HttpStatus statusCode;
    private String message;
    private RecipientData data;
}
