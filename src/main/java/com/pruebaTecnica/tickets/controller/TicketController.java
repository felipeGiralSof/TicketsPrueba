package com.pruebaTecnica.tickets.controller;

import com.pruebaTecnica.tickets.dto.TicketRestDto;
import com.pruebaTecnica.tickets.model.TicketModel;
import com.pruebaTecnica.tickets.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TicketModel> getAllTickets(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size){
        Page<TicketModel> pageTicketModel = this.ticketService.getAllTickets(PageRequest.of(page, size));
        return pageTicketModel.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketModel> getTicketById(@PathVariable Long id){
        return this.ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TicketModel addTicket(@RequestBody TicketRestDto ticket){
        TicketModel ticketModel = this.modelMapper.map(ticket, TicketModel.class);
        return this.ticketService.addTicket(ticketModel);
    }

    @PutMapping("/{id}")
    public TicketModel updateTicket(@PathVariable Long id, @RequestBody TicketModel ticketUpdated){
        return this.ticketService.updateTicket(id, ticketUpdated);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id){
        return this.ticketService.deleteTicket(id);
    }

}
