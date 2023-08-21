package com.alaindroid.sportsbet.model;

import java.math.BigDecimal;

public record Ticket(TicketType ticketType, int quantity, BigDecimal totalCost) {
}
