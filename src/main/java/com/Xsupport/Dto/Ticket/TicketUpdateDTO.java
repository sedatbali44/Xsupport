package com.Xsupport.Dto.Ticket;

import com.Xsupport.Entity.Status;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateDTO {
    private Status status;

    @Size(max = 1000, message = "Admin response cannot exceed 1000 characters")
    private String adminResponse;
}