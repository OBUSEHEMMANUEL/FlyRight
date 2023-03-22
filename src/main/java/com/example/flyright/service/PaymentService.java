package com.example.flyright.service;

import com.example.flyright.dto.request.PaymentRequest;
import com.example.flyright.dto.response.PaymentResponse;

import java.io.IOException;

public interface PaymentService {
   PaymentResponse payment (PaymentRequest paymentRequest) throws IOException;
}
