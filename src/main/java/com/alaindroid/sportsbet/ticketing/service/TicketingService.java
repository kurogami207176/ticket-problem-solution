package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.model.Ticket;
import com.alaindroid.sportsbet.model.TicketType;
import com.alaindroid.sportsbet.pricing.service.PricingService;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketingService {
    @Autowired
    private PricingService pricingService;
    @Autowired
    private CustomerCategoriserService customerCategoriserService;
    @Autowired
    private DiscountServices discountServices;

    public TicketingResponse getTickets(TicketingRequest ticketingRequest) {
        Map<TicketType, Long> ticketCounts = ticketingRequest.customers().stream()
                .map(customerCategoriserService::getTicketType)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Ticket> tickets = ticketCounts.entrySet().stream()
                .map(entry -> createTicket(entry.getKey(), entry.getValue()))
                .map(discountServices::applyDiscounts)
                .collect(Collectors.toList());
        BigDecimal totalCost = tickets.stream()
                .map(Ticket::totalCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
        return new TicketingResponse(tickets, totalCost);
    }

    private Ticket createTicket(TicketType type, Long count) {
        BigDecimal unitPrice = pricingService.getPrice(type);
        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(count))
                .setScale(2, RoundingMode.HALF_UP);
        return new Ticket(type, count.intValue(), totalPrice);
    }
}
