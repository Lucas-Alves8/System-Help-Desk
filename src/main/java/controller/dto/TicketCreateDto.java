package controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import model.enums.TicketCategory;
import model.enums.TicketPriority;

public record TicketCreateDto(
        @NotBlank(message = "Commentary is necessary")
        String commentary,

        @NotBlank(message = "Title is necessary")
        String title,

        @NotNull(message = "Priority is necessary")
        TicketPriority ticketPriority,

        @NotNull(message = "Category is necessary")
        TicketCategory ticketCategory,

        @NotBlank(message = "Requester is necessary")
        String requester) {
}

