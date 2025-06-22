package com.Xsupport.Dto.Ticket;


import com.Xsupport.Dto.User.UserDTO;
import com.Xsupport.Entity.Category;
import com.Xsupport.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSearchDTO {

    private String title;
    private String description;
    private Category category;
    private Status status;
    private String adminResponse;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private UserDTO user;
}
