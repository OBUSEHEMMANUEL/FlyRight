package com.example.flyright.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@Data
@Setter
@Getter
public class RecipientData {
    private String name;
    private String description;
    private BigDecimal amount;
    private String integration;
    private String domain;
    private String slug;
    private String currency;
    private String type;
    private Boolean collect_phone;
   private Boolean active;
   private Boolean published;
   private Boolean migrate;
   private Long id;
   private String createdAt;
   private String updatedAt;
}
