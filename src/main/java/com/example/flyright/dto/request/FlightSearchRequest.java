package com.example.flyright.dto.request;

import com.example.flyright.data.constant.TicketType;
import lombok.Data;

@Data
public class FlightSearchRequest {
    private String destination;
    private String origin;
    private String tripType;
    private String numberOfPassenger;
    private String flightCategory;
    private String departureDateTime;
    private String returnDateType;
}
