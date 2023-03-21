package com.example.flyright.service;

import com.example.flyright.data.model.Passenger;
import com.example.flyright.dto.request.ConfirmTokenRequest;
import com.example.flyright.dto.request.LoginRequest;
import com.example.flyright.dto.request.PassengerRegistrationRequest;
import com.example.flyright.dto.response.LoginResponse;
import com.example.flyright.dto.response.PassengerRegistrationResponse;

public interface PassengerService {
    PassengerRegistrationResponse register(PassengerRegistrationRequest request);
    String generateToken(Passenger passenger);
    String confirmToken(ConfirmTokenRequest confirmationToken);
    LoginResponse login(LoginRequest loginRequest);
}
