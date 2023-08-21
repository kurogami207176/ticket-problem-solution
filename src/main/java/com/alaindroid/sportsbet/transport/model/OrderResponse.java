package com.alaindroid.sportsbet.transport.model;

import com.alaindroid.sportsbet.model.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record OrderResponse(int transactionId, List<Ticket> tickets, BigDecimal totaCost) {
    public OrderResponse {
        Objects.requireNonNull(tickets, "tickets cannot be null");
        Objects.requireNonNull(totaCost, "name cannot be null");
    }

}
