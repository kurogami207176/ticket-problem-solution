package com.alaindroid.sportsbet.ticketing.model;

import com.alaindroid.sportsbet.model.Ticket;

import java.math.BigDecimal;
import java.util.List;

public record TicketingResponse(List<Ticket> tickets, BigDecimal totalCost) {
}
