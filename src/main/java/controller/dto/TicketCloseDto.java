package controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.enums.TicketStatus;


public record TicketCloseDto(String responsible,
                             TicketStatus ticketStatus) {
}
