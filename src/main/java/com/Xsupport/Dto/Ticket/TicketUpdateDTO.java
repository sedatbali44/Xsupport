package com.Xsupport.Dto.Ticket;

import com.Xsupport.Entity.Category;
import com.Xsupport.Entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateDTO {

    private String title;
    private String description;
    private Category category;
    private Status status;
    private String adminResponse;
    private Long id;
}