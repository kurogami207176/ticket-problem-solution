package com.alaindroid.sportsbet.ticketing.model;

import com.alaindroid.sportsbet.common.model.TicketType;

public record CustomerRange (TicketType type, Integer minimumAge) implements Comparable<CustomerRange> {
    @Override
    public int compareTo(CustomerRange o) {
        return this.minimumAge.compareTo(o.minimumAge);
    }
}
