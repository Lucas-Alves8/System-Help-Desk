package controller;

import controller.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import model.Ticket;
import org.springframework.web.bind.annotation.*;
import service.TicketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public Ticket ticketCreate(@RequestBody @Valid TicketCreateDto ticketCreateDto) {
        return ticketService.ticketCreate(ticketCreateDto);
    }

    @PostMapping("/{id}")
    public Ticket ticketUpdate(@PathVariable UUID id, @RequestBody @Valid TicketUpdateDto ticketUpdateDto) {
        return ticketService.ticketUpdate(id, ticketUpdateDto);
    }

    @GetMapping
    public List<TicketFindAllDto> findAll () {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public TicketFindByIdDto findById(@PathVariable UUID id) {
        return ticketService.findById(id);
    }

    @PutMapping("/{id}/close")
    public TicketCloseDto ticketClose(@PathVariable UUID id, @RequestBody @Valid TicketCloseDto ticketCloseDto) {
        return ticketService.ticketClose(id, ticketCloseDto.responsible());
    }
}
