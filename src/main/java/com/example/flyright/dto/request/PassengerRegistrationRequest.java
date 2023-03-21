package com.example.flyright.dto.request;

import lombok.Data;

@Data
public class PassengerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String dob;
    private String nationality;
    private String emailAddress;
    private String phoneNumber;
    private String password;
}
