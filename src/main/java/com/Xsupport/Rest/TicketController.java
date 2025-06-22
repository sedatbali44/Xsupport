package com.Xsupport.Rest;

import com.Xsupport.Dto.Ticket.TicketCreateDTO;
import com.Xsupport.Dto.Ticket.TicketDTO;
import com.Xsupport.Dto.Ticket.TicketSearchDTO;
import com.Xsupport.Entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketController {

    ResponseEntity<TicketDTO> create(TicketCreateDTO request);

    ResponseEntity<List<Ticket>> findTicketsWithFilter(TicketSearchDTO request);
}
