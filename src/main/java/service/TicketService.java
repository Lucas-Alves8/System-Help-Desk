package service;

import controller.dto.*;
import lombok.RequiredArgsConstructor;
import mapper.TicketMapper;
import model.Ticket;
import model.enums.TicketCategory;
import model.enums.TicketPriority;
import model.enums.TicketStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.TicketRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'SUPPORTTI')")
    public Ticket create(TicketCreateDto ticketCreateDto) {

        Ticket ticket = ticketMapper.toEntity(ticketCreateDto);
        return ticketRepository.save(ticket);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPORTTI')")
    public void update(UUID id, TicketUpdateDto ticketUpdateDto) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found or not existed"));

        ticketMapper.toEntity(ticketUpdateDto, ticket);

        ticketRepository.save(ticket);

    }

    @PreAuthorize("hasRole('ADMIN')")
    public Ticket deleteTicket(UUID id) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticketRepository.delete(ticket);
        return ticket;
    }

    public Ticket timeLimit(Ticket ticket) {

        if (ticket.getTicketStatus() != TicketStatus.OPEN) {
            return ticket;
        }

        LocalDateTime now = LocalDateTime.now();
        long hours = Duration.between(ticket.getCreatedAt(), now).toHours();

        if (hours >= 28) {
            ticket.setTicketPriority(TicketPriority.URGENCY);
        } else if (hours >= 24) {
            ticket.setTicketPriority(TicketPriority.HIGH);
        } else if (hours >= 20) {
            ticket.setTicketPriority(TicketPriority.MEDIUM);
        } else if (hours >= 16) {
            ticket.setTicketPriority(TicketPriority.LOW);
        }
        return ticket;
    }

    public List<TicketFindAllDto> findAll() {

        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::tofindAll)
                .toList();
    }

    public TicketFindByIdDto findById(UUID id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found or not existed"));

        return ticketMapper.toFindById(ticket);

    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPORTTI')")
    public TicketCloseDto finishTicket(UUID id, String responsible) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getTicketStatus() != TicketStatus.OPEN)
            throw new RuntimeException("Ticket not created or not open");

        ticket.setResponsible(responsible);
        ticket.setTicketStatus(TicketStatus.CLOSED);
        ticket.setClosedAt(LocalDateTime.now());

        ticketRepository.save(ticket);

        return ticketMapper.toCloseDto(ticket);
    }
}
