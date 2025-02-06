package com.pruebaTecnica.tickets.service.impl;

import com.pruebaTecnica.tickets.model.TicketModel;
import com.pruebaTecnica.tickets.repository.TicketRepository;
import com.pruebaTecnica.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Page<TicketModel> getAllTickets(Pageable pageable) {
        return this.ticketRepository.findAll(pageable);
    }

    @Override
    public Optional<TicketModel> getTicketById(Long id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public TicketModel addTicket(TicketModel ticket) {
        return this.ticketRepository.save(ticket);
    }

    @Override
    public TicketModel updateTicket(Long id, TicketModel ticketUpdated) {
        return this.ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setUsuario(ticketUpdated.getUsuario());
                    ticket.setEstatus(ticketUpdated.getEstatus());
                    return this.ticketRepository.save(ticket);
                })
                .orElseGet(() -> {
                    ticketUpdated.setId(id);
                    return ticketRepository.save(ticketUpdated);
                });
    }

    @Override
    public String deleteTicket(Long id) {
        this.ticketRepository.deleteById(id);
        return "ticket con id " + id + " eliminado con exito";
    }
}
