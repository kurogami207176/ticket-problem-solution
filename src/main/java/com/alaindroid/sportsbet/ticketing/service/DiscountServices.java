package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.model.Ticket;
import com.alaindroid.sportsbet.model.TicketType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountServices {

    private static final BigDecimal CHILDREN_DISCOUNT_SCALE = BigDecimal.valueOf(0.75);

    public Ticket applyDiscounts(Ticket ticket) {
        if (ticket.ticketType() == TicketType.CHILDREN && ticket.quantity() >= 3) {
            return new Ticket(ticket.ticketType(), ticket.quantity(), ticket.totalCost().multiply(CHILDREN_DISCOUNT_SCALE));
        }
        return ticket;
    }
}
