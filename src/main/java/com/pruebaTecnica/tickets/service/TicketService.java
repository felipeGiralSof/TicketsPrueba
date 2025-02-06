package com.pruebaTecnica.tickets.service;

import com.pruebaTecnica.tickets.model.TicketModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TicketService {
    Page<TicketModel> getAllTickets(Pageable pageable);
    Optional<TicketModel> getTicketById(Long id);
    TicketModel addTicket(TicketModel ticket);
    TicketModel updateTicket(Long id, TicketModel ticketUpdated);
    String deleteTicket(Long id);
}
