package com.example.flyright.data.repositories;

import com.example.flyright.data.model.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PassengerRepo extends MongoRepository<Passenger, String> {
    Optional<Passenger> findByEmailAddressIgnoreCase(String emailAddress);
}
