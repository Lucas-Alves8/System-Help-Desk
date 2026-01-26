package controller.dto;

import model.enums.TicketCategory;
import model.enums.TicketPriority;

import java.util.UUID;

public record TicketFindByIdDto(String commentary,
                                String title,
                                String requester,
                                TicketPriority ticketPriority,
                                TicketCategory ticketCategory) {
}
