package com.pruebaTecnica.tickets.graphql;

import com.pruebaTecnica.tickets.dto.TicketDto;
import com.pruebaTecnica.tickets.model.TicketModel;
import com.pruebaTecnica.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TicketQueryResolver {

    @Autowired
    private TicketService ticketService;

    @QueryMapping
    public TicketModel getTicketById(@Argument Long id){
        return this.ticketService.getTicketById(id).orElse(null);
    }

    @QueryMapping
    public Page<TicketDto> getAllTickets(@Argument int page, @Argument int size){
        Page<TicketModel> ticketModels = this.ticketService.getAllTickets(PageRequest.of(page, size));
        List<TicketDto> ticketDtos = ticketModels.getContent().stream()
                .map(this::convertirLocalDateTimeAString)
                .collect(Collectors.toList());
        return new PageImpl<>(ticketDtos, ticketModels.getPageable(), ticketModels.getTotalElements());
    }

    private TicketDto convertirLocalDateTimeAString(TicketModel ticket) {
        if (ticket == null) {
            return null;
        }
        TicketDto ticketDto = new TicketDto();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        ticketDto.setFechaCreacion(ticket.getFechaCreacion().format(formatter));
        ticketDto.setFechaActualizacion(ticket.getFechaActualizacion().format(formatter));
        ticketDto.setEstatus(ticket.getEstatus());
        ticketDto.setUsuario(ticket.getUsuario());
        ticketDto.setId(ticket.getId());
        return ticketDto;
    }
}
