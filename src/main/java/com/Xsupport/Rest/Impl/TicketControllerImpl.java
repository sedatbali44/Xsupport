package com.Xsupport.Rest.Impl;

import com.Xsupport.Dto.Ticket.TicketCreateDTO;
import com.Xsupport.Dto.Ticket.TicketDTO;
import com.Xsupport.Dto.Ticket.TicketSearchDTO;
import com.Xsupport.Dto.Ticket.TicketUpdateDTO;
import com.Xsupport.Entity.Ticket;
import com.Xsupport.Rest.TicketController;
import com.Xsupport.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

    @Autowired
    private TicketService ticketService;


    @Override
    @PostMapping("/create")
    public ResponseEntity<TicketDTO> create(@RequestBody TicketCreateDTO request) {
        return ResponseEntity.ok(ticketService.create(request));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<TicketDTO> update(@RequestBody TicketUpdateDTO request) {
        return ResponseEntity.ok(ticketService.update(request));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TicketDTO>> findTicketsWithFilter(@Validated TicketSearchDTO request, @PageableDefault(size = 10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ticketService.findTicketsWithFilter(request, pageable));
    }


    @Override
    @GetMapping("/own-tickets")
    public ResponseEntity<List<Ticket>> findByCurrentUser() {
        return ResponseEntity.ok(ticketService.findByCurrentUser());
    }
}
