package com.example.flyright.service;

import com.example.flyright.data.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken confirmationToken);
    Optional<ConfirmationToken> getConfirmationToken(String token);
    void deleteExpiredToken();
    void setConfirmed(String token);
}
