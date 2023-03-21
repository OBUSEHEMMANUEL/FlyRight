package com.example.flyright.data.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Passenger {
    @Id
    private String id;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String firstName;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String lastName;
    private String dob;
    private String nationality;
    @Email(message="This field requires a valid email address")
    private String emailAddress;
    @Pattern(regexp = "\\d{11}")
    private String phoneNumber;
    private String password;
}
