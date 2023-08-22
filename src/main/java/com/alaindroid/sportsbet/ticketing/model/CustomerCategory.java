package com.alaindroid.sportsbet.ticketing.model;

import com.alaindroid.sportsbet.common.model.TicketType;

import java.util.Objects;

public record CustomerCategory (int minimumAge, TicketType ticketType) {
    public CustomerCategory {
        Objects.requireNonNull(ticketType);
    }
}
