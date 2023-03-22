package com.example.flyright.config;

import com.example.flyright.data.model.Passenger;
import com.example.flyright.data.repositories.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class PassengerInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private PassengerRepo passengerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Passenger> passengerInfo = passengerRepo.findByEmailAddressIgnoreCase(username);
        return passengerInfo.map(PassengerInfo::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
