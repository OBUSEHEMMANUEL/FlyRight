package com.example.flyright.service;

import com.example.flyright.dto.request.ConfirmTokenRequest;
import com.example.flyright.dto.request.LoginRequest;
import com.example.flyright.dto.request.PassengerRegistrationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PassengerServiceImplTest {
    @Autowired
    PassengerService passengerService;
    PassengerRegistrationRequest passenger;

    ConfirmTokenRequest tokenRequest;


    @BeforeEach
    void setUp() {
        passenger = new PassengerRegistrationRequest();

        passenger.setFirstName("Derek");
        passenger.setLastName("Bolaji");
        passenger.setNationality("Nigeria");
        passenger.setPhoneNumber("07023453617");
        passenger.setPassword("1234567");
        passenger.setEmailAddress("obusehemmanuel208@gmail.com");
        passenger.setDob("12/03/2023");
    }

    @Test
    void register() {

        assertEquals(HttpStatus.CREATED,passengerService.register(passenger).getStatusCode());
    }

    @Test
    void confirmPassword(){
        passenger = new PassengerRegistrationRequest();

        passenger.setFirstName("Derek");
        passenger.setLastName("Bolaji");
        passenger.setNationality("Nigeria");
        passenger.setPhoneNumber("07023453617");
        passenger.setPassword("1234567");
        passenger.setEmailAddress("obusehemmanuel208@gmail.com");
        passenger.setDob("12/03/2023");
      var response =  passengerService.register(passenger);

        tokenRequest = new ConfirmTokenRequest();
        tokenRequest.setToken(response.getToken());
        tokenRequest.setEmailAddress("obusehemmanuel208@gmail.com");

        assertEquals( "confirmed",passengerService.confirmToken(tokenRequest));

    }
    @Test
    void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("1234567");
        loginRequest.setEmailAddress("obusehemmanuel208@gmail.com");

        assertEquals(HttpStatus.OK,passengerService.login(loginRequest).getStatusCode());
    }

}