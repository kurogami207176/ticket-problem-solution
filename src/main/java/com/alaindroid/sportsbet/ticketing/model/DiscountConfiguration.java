package com.alaindroid.sportsbet.ticketing.model;

import com.alaindroid.sportsbet.common.model.TicketType;

import java.util.Map;

public record DiscountConfiguration (Map<TicketType, TicketDiscount> tickets) {
}
