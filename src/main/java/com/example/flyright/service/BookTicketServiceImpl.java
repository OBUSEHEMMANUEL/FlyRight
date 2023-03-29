package com.example.flyright.service;

import com.example.flyright.data.model.BookTicket;
import com.example.flyright.data.repositories.BookTicketRepository;
import com.example.flyright.dto.request.BookingRequest;
import com.example.flyright.dto.response.BookingResponse;
import com.example.flyright.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class BookTicketServiceImpl implements BookTicketService {

    @Autowired
    BookTicketRepository bookTicketRepository;
    @Autowired
    EmailSenderService emailSenderService;

    public BookingResponse bookFlight(BookingRequest bookingRequest) {

        String ticketNumber= UUID.randomUUID().toString().substring(0,8);

       BookTicket bookTicket = new BookTicket();

       bookTicket.setPassengerName(bookingRequest.getPassengerName());
       bookTicket.setBookingNumber(ticketNumber);
       bookTicket.setNumSeatsBooked(bookingRequest.getNumSeatsBooked());
       bookTicket.setEmailAddress(bookingRequest.getEmailAddress());

        bookTicketRepository.save(bookTicket);

        emailSenderService.send(bookingRequest.getEmailAddress(), ticketEmail(bookingRequest,ticketNumber));
        BookingResponse response = new BookingResponse();
        response.setTicketNumber(ticketNumber);
        response.setMessage("Booked Successfully");
        response.setStatus(HttpStatus.OK);
        return response;

    }
    private String ticketEmail(BookingRequest bookingRequest, String ticketNumber){
        String subject = "Flight Booking Confirmation";
        return  "Dear " + bookingRequest.getPassengerName() + "," +
                "\n\nThank you for booking your flight with us. Here are your booking details:\n\nFlight Number:" +
                " " + ticketNumber +
                "\nNumber of Seats Booked: " + bookingRequest.getNumSeatsBooked() +
                "\n\nPlease keep this email as your booking confirmation." +
                "\n\nThank you,\nThe Flight Booking Team";
    }

}
