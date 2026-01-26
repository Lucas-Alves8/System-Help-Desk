package controller.dto;

import model.enums.TicketCategory;
import model.enums.TicketPriority;

public record TicketFindAllDto(String commentary,
                               String title,
                               String requester,
                               TicketPriority ticketPriority,
                               TicketCategory ticketCategory) {
}
