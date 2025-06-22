package com.Xsupport.Service;

import com.Xsupport.Dto.Ticket.TicketCreateDTO;
import com.Xsupport.Dto.Ticket.TicketDTO;
import com.Xsupport.Dto.Ticket.TicketSearchDTO;
import com.Xsupport.Dto.Ticket.TicketUpdateDTO;
import com.Xsupport.Entity.Category;
import com.Xsupport.Entity.Status;
import com.Xsupport.Entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketService {

    List<Ticket> findByUser(Long userId);

    List<Ticket> findByCurrentUser();

    Page<TicketDTO> findTicketsWithFilter(TicketSearchDTO request, Pageable pageable);

    TicketDTO create(TicketCreateDTO request);

    TicketDTO update(TicketUpdateDTO request);

    TicketDTO mapToDTO(Ticket ticket);
}
