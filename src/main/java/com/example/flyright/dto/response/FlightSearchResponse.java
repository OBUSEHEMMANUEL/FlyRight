package com.example.flyright.dto.response;

import lombok.*;


import java.util.List;

@Data
public class FlightSearchResponse {
    private List<Object> prices;
    private List<String> origins;
    private List<String> destinations;
}
