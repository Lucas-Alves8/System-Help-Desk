package service;

import lombok.RequiredArgsConstructor;
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

    private final TicketRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'SUPPORTTI')")
    public Ticket createTicket(String commentary, String title, TicketPriority ticketPriority, TicketCategory ticketCategory, String requester) {

        Ticket ticket = new Ticket();

        ticket.setTitle(title);
        ticket.setCommentary(commentary);
        ticket.setTicketPriority(ticketPriority);
        ticket.setTicketCategory(ticketCategory);
        ticket.setRequester(requester);
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());

        return repository.save(ticket);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPORTTI')")
    public Ticket updateTicket(UUID id, TicketPriority ticketPriority, String responsible, String commentary) {

        Ticket ticket = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getTicketStatus() != TicketStatus.OPEN) throw new RuntimeException("Ticket not created or not open");

        ticket.setTicketPriority(ticketPriority);
        ticket.setResponsible(responsible);
        ticket.setCommentary(commentary);

        return repository.save(ticket);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public Ticket deleteTicket(UUID id) {

        Ticket ticket = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        repository.delete(ticket);
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

    public List<Ticket> findAll() {

        return repository.findAll();
    }

    public Ticket findById(UUID id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found or not existed"));

    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPORTTI')")
    public Ticket finishTicket(UUID id, String responsible, TicketStatus ticketStatus) {

        Ticket ticket = repository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getTicketStatus() != TicketStatus.OPEN) throw new RuntimeException("Ticket not created or not open");

        ticket.setResponsible(responsible);
        ticket.setTicketStatus(TicketStatus.CLOSED);
        ticket.setClosedAt(LocalDateTime.now());

        return repository.save(ticket);
    }
}
