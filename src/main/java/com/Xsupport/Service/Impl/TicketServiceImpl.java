package com.Xsupport.Service.Impl;


import com.Xsupport.Dto.Ticket.TicketCreateDTO;
import com.Xsupport.Dto.Ticket.TicketDTO;
import com.Xsupport.Dto.Ticket.TicketSearchDTO;
import com.Xsupport.Entity.Role;
import com.Xsupport.Entity.Status;
import com.Xsupport.Entity.Ticket;
import com.Xsupport.Entity.User;
import com.Xsupport.Exception.ExceptionMessage;
import com.Xsupport.Repo.TicketRepository;
import com.Xsupport.Service.TicketService;
import com.Xsupport.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repo;

    @Autowired
    private UserService userService;


    @Override
    public List<Ticket> findByUser(Long userId) {
        return repo.findByUser(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ticket> findByCurrentUser() {
        User user = userService.getCurrentUser();
        return findByUser(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TicketDTO> findTicketsWithFilter(TicketSearchDTO request, Pageable pageable) {
        User user = userService.getCurrentUser();
        if (user.getRole() != Role.ADMIN) {
            throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
        }

        Page<Ticket> tickets = repo.findTicketsWithFilter(request.getCategory(), request.getStatus(), pageable);
        return tickets.map(this::mapToDTO);
    }


    @Override
    public TicketDTO create(TicketCreateDTO request) {
        User user = userService.getCurrentUser();

        Ticket ticket = new Ticket();
        ticket.setCreatedTime(LocalDateTime.now());
        ticket.setUser(user);
        ticket.setDescription(request.getDescription());
        ticket.setCategory(request.getCategory());
        ticket.setTitle(request.getTitle());
        ticket.setStatus(Status.OPEN);
        repo.save(ticket);

        return mapToDTO(ticket);
    }

    @Override
    public TicketDTO mapToDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .category(ticket.getCategory())
                .status(ticket.getStatus())
                .adminResponse(ticket.getAdminResponse())
                .createdTime(ticket.getCreatedTime())
                .updatedTime(ticket.getUpdatedTime())
                .userId(ticket.getUser().getId())
                .build();
    }

}
