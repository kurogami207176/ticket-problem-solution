package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.common.model.Ticket;
import com.alaindroid.sportsbet.ticketing.model.DiscountConfiguration;
import com.alaindroid.sportsbet.ticketing.model.TicketDiscount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class DiscountServices {

    private DiscountConfiguration configuration;

    public Ticket applyDiscounts(Ticket ticket) {
        TicketDiscount ticketDiscount = configuration.tickets().get(ticket.ticketType());
        if (ticketDiscount == null || ticket.quantity() < ticketDiscount.minimumQuantity()) {
            return ticket;
        }
        BigDecimal scale = BigDecimal.ONE.subtract(ticketDiscount.discount());
        BigDecimal discountedPrice = ticket.totalCost().multiply(scale);
        return new Ticket(ticket.ticketType(), ticket.quantity(), discountedPrice);
    }
}
