package com.example.flyright.service;

import com.example.flyright.data.model.ConfirmationToken;
import com.example.flyright.data.model.Passenger;
import com.example.flyright.data.repositories.PassengerRepo;
import com.example.flyright.dto.request.ConfirmTokenRequest;
import com.example.flyright.dto.request.LoginRequest;
import com.example.flyright.dto.request.PassengerRegistrationRequest;
import com.example.flyright.dto.request.PaymentRequest;
import com.example.flyright.dto.response.LoginResponse;
import com.example.flyright.dto.response.PassengerRegistrationResponse;
import com.example.flyright.dto.response.PaymentResponse;
import com.example.flyright.service.email.EmailSenderService;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.json.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    PassengerRepo passengerRepo;
    @Autowired
    ConfirmationTokenService confirmationTokenService;


    @Autowired
    EmailSenderService emailService;



    @Override
    public PassengerRegistrationResponse register(PassengerRegistrationRequest request) {
        boolean emailExist = passengerRepo.findByEmailAddressIgnoreCase (request.getEmailAddress()).isPresent();
        if (emailExist) throw new IllegalStateException("Email Address Already Exist");
var hashed = bcrypt(request.getPassword());

        Passenger passenger = new Passenger();
        passenger.setDob(request.getDob());
        passenger.setEmailAddress(request.getEmailAddress());
        passenger.setFirstName(request.getFirstName());
        passenger.setNationality(request.getNationality());
        passenger.setPassword(hashed);
        passenger.setLastName(request.getLastName());
        passenger.setPhoneNumber(request.getPhoneNumber());
        passengerRepo.save(passenger);
       String token = generateToken(passenger);

        PassengerRegistrationResponse response = new PassengerRegistrationResponse();
        response.setMessage("Created Successfully");
        response.setStatusCode(HttpStatus.CREATED);
        response.setToken(token);
        emailService.send(request.getEmailAddress(),buildEmail(request.getEmailAddress(),token));
        return response;
    }
    public String bcrypt(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        var hashedPassword =   encoder.encode(password);
        return  hashedPassword;

    }
    @Override
    public String generateToken(Passenger passenger) {
        StringBuilder tok = new StringBuilder();
        SecureRandom number = new SecureRandom();
        for (int i = 0; i < 4; i++) {
            int num = number.nextInt(9);
            tok.append(num);
        }
        StringBuilder token = new StringBuilder(tok.toString());
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(String.valueOf(token));
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiredAt(LocalDateTime.now().plusMinutes(10));
        confirmationToken.setPassenger(passenger);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token.toString();
    }
@Override
    public String confirmToken(ConfirmTokenRequest confirmationToken){
        var token = confirmationTokenService.getConfirmationToken(confirmationToken.getToken())
                .orElseThrow(()-> new IllegalStateException("Token does not exist"));

        if (token.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token has expired");
        }
        confirmationTokenService.setConfirmed(token.getToken());

        return "confirmed";
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var foundUser = passengerRepo.findByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(() -> new RuntimeException("email not found"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        var matches =   encoder.matches(loginRequest.getPassword(), foundUser.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        if(matches) {
            loginResponse.setStatusCode(HttpStatus.OK);
            loginResponse.setMessage("LOGIN SUCCESSFULLY");
        } else {
            loginResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            loginResponse.setMessage("INVALID EMAIL OR PASSWORD");
        }
        return loginResponse;
    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" +
                " <p>"+ link + " </p> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


}
