package controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.enums.TicketCategory;
import model.enums.TicketPriority;

public record TicketUpdateDto(
        @NotBlank(message = "Commentary is necessary")
        String commentary,

        @NotBlank(message = "Title is necessary")
        String title,

        @NotBlank(message = "Responsible is necessary")
        String responsible,

        @NotNull(message = "Ticket priority is necessary")
        TicketPriority ticketPriority,

        @NotNull(message = "Ticket priority is necessary")
        TicketCategory ticketCategory
        ){
}
