package com.example.flyright.service;

import com.example.flyright.data.model.ConfirmationToken;
import com.example.flyright.data.repositories.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }


    public Optional<ConfirmationToken> getConfirmationToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }
    public  void deleteExpiredToken(){
        confirmationTokenRepository.deleteConfirmationTokensByExpiredAtBefore(LocalDateTime.now());
    }
    public  void setConfirmed(String token ){
                confirmationTokenRepository.confirmAt(LocalDateTime.now(),token);
    }
}
