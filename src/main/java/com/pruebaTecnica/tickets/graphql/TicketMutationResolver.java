package com.pruebaTecnica.tickets.graphql;

import com.pruebaTecnica.tickets.dto.Estatus;
import com.pruebaTecnica.tickets.model.TicketModel;
import com.pruebaTecnica.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TicketMutationResolver {

    @Autowired
    private TicketService ticketService;

    @MutationMapping
    public TicketModel addTicket(@Argument String usuario, @Argument String estatus) {
        TicketModel ticket = new TicketModel();
        ticket.setUsuario(usuario);
        ticket.setEstatus(Estatus.valueOf(estatus));
        return ticketService.addTicket(ticket);
    }

    @MutationMapping
    public TicketModel updatedTicket(@Argument Long id, @Argument String usuario, @Argument String estatus) {
        TicketModel ticket = new TicketModel();
        ticket.setUsuario(usuario);
        ticket.setEstatus(Estatus.valueOf(estatus));
        return ticketService.updateTicket(id, ticket);
    }

    @MutationMapping
    public Boolean deleteTicket(@Argument Long id) {
        ticketService.deleteTicket(id);
        return true;
    }
}
