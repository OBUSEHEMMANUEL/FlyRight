package com.example.flyright.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class BookTicket {
    @Id
    private String  id;
    private String passengerName;
    private String bookingNumber;
    private int numSeatsBooked;
    private String emailAddress;
}
