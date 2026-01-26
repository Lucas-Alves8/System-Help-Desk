package mapper;

import controller.dto.*;
import model.Ticket;
import model.enums.TicketCategory;
import model.enums.TicketPriority;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TicketMapper {

    public Ticket toEntity (TicketCreateDto ticketCreateDto) {

        Ticket ticket = new Ticket();
        ticket.setCommentary(ticketCreateDto.commentary());
        ticket.setTitle(ticketCreateDto.title());
        ticket.setTicketPriority(ticketCreateDto.ticketPriority());
        ticket.setTicketCategory(ticketCreateDto.ticketCategory());
        ticket.setRequester(ticketCreateDto.requester());

        return ticket;
    }

    public void toEntity (TicketUpdateDto ticketUpdateDto, Ticket ticket) {

        ticket.setCommentary(ticketUpdateDto.commentary());
        ticket.setTitle(ticketUpdateDto.title());
        ticket.setResponsible(ticketUpdateDto.responsible());
        ticket.setTicketPriority(ticketUpdateDto.ticketPriority());
        ticket.setTicketCategory(ticketUpdateDto.ticketCategory());

    }

    public TicketFindAllDto tofindAll (Ticket ticket) {

        return  new TicketFindAllDto(
                ticket.getCommentary(),
                ticket.getTitle(),
                ticket.getRequester(),
                ticket.getTicketPriority(),
                ticket.getTicketCategory()
        );
    }

    public TicketFindByIdDto toFindById (Ticket ticket) {

        return new TicketFindByIdDto(
                ticket.getCommentary(),
                ticket.getTitle(),
                ticket.getRequester(),
                ticket.getTicketPriority(),
                ticket.getTicketCategory()
        );
    }

    public TicketCloseDto toCloseDto (Ticket ticket) {

        return new TicketCloseDto(
                ticket.getResponsible(),
                ticket.getTicketStatus()
        );
    }
}
