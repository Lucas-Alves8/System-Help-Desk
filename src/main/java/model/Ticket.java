package model;

import jakarta.persistence.*;
import lombok.Data;
import model.enums.TicketCategory;
import model.enums.TicketPriority;
import model.enums.TicketStatus;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {

    @UuidGenerator
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 300)
    private String commentary;

    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 25)
    private TicketPriority ticketPriority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 25)
    private TicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 25)
    private TicketCategory ticketCategory;

    @Column(name = "responsible", nullable = false, length = 60)
    private String responsible;

    @Column(name = "requester", nullable = false, length = 60)
    private String requester;

    @Column(name = "creationAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "closedAt", nullable = false)
    private LocalDateTime closedAt;

}
