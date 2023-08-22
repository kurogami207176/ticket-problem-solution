package com.alaindroid.sportsbet.ticketing.model;

import java.math.BigDecimal;

public record TicketDiscount (int minimumQuantity, BigDecimal discount) {
}
