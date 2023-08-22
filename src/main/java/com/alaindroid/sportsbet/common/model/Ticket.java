package com.alaindroid.sportsbet.common.model;

import java.math.BigDecimal;

public record Ticket(TicketType ticketType, int quantity, BigDecimal totalCost) {
}
