package com.example.flyright.dto.request;

import lombok.Data;

@Data
public class BookingRequest {
    private String title;
    private String firstName;
  private   String lastName;
  private String middleName;
  private String dob;
  private String nationality;
  private String phoneNumber;
   private String emailAddress;
    private int seatNumBooked;
}
