package com.example.flyright.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class BookTicket {
    @Id
    private String  id;
    private String title;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dob;
    private String nationality;
    private String phoneNumber;
    private String emailAddress;
    private int seatNumBooked;
    private String ticketNUmber;
}
