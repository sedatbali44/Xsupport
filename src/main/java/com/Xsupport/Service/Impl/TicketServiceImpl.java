package com.Xsupport.Service.Impl;


import com.Xsupport.Dto.Ticket.TicketCreateDTO;
import com.Xsupport.Dto.Ticket.TicketDTO;
import com.Xsupport.Dto.Ticket.TicketSearchDTO;
import com.Xsupport.Dto.Ticket.TicketUpdateDTO;
import com.Xsupport.Entity.*;
import com.Xsupport.Exception.ExceptionMessage;
import com.Xsupport.Repo.TicketRepository;
import com.Xsupport.Service.TicketService;
import com.Xsupport.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.Xsupport.Entity.ResponseMessage.RECORD_DELETED;


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
    public List<TicketDTO> findByCurrentUser() {
        User user = userService.getCurrentUser();
        List<Ticket> tickets = findByUser(user.getId());
        return tickets.stream().map(this::mapToDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TicketDTO> findTicketsWithFilter(TicketSearchDTO request, Pageable pageable) {
        Boolean isAuthorized = userService.isAuthorized();
        if (!isAuthorized) {
            throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
        }

        Page<Ticket> tickets = repo.findTicketsWithFilter(
                request.getCategory(),
                request.getStatus(),
                request.getTitle(),
                pageable
        );

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
    public TicketDTO update(TicketUpdateDTO request) {

        Boolean isAuthorized = userService.isAuthorized();
        if (!isAuthorized) {
            throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
        }

        Ticket ticket = repo.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        ExceptionMessage.RECORD_NOT_FOUND.getMessage() + " " + request.getId()
                ));

        ticket.setDescription(request.getDescription());
        ticket.setCategory(request.getCategory());
        ticket.setTitle(request.getTitle());
        ticket.setStatus(request.getStatus());
        ticket.setUpdatedTime(LocalDateTime.now());
        ticket.setAdminResponse(request.getAdminResponse());
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
                .userName(ticket.getUser().getUsername())
                .build();
    }

    @Override
    public String delete(String id) {
        try {
            Boolean isAuthorized = userService.isAuthorized();
            if (!isAuthorized) {
                throw new AccessDeniedException(ExceptionMessage.UNAUTHORIZED_ACTION.getMessage());
            }
            long parsedId = Long.parseLong(id);
            if (!repo.existsById(parsedId)) {
                throw new EntityNotFoundException(ExceptionMessage.RECORD_NOT_FOUND + id);
            }
            repo.deleteById(parsedId);
            return ResponseMessage.RECORD_DELETED.getValue();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_CREDENTIALS + id, e);
        }
    }

}
