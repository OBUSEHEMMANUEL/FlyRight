package com.example.flyright.data.repositories;

import com.example.flyright.data.model.BookTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookTicketRepository extends MongoRepository<BookTicket, String> {
}
