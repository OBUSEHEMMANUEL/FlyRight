package com.example.flyright.data.repositories;

import com.example.flyright.data.model.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken,String> {
    Optional<ConfirmationToken> findByToken(String token);
    void deleteConfirmationTokensByExpiredAtBefore(LocalDateTime current);
    void confirmAt(LocalDateTime now, String confirmationToken);
}
