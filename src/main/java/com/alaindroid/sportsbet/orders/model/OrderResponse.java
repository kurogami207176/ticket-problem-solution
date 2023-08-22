package com.alaindroid.sportsbet.orders.model;

import com.alaindroid.sportsbet.common.model.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record OrderResponse(int transactionId, List<Ticket> tickets, BigDecimal totalCost) {
    public OrderResponse {
        Objects.requireNonNull(tickets, "tickets cannot be null");
        Objects.requireNonNull(totalCost, "totalCost cannot be null");
    }

}
