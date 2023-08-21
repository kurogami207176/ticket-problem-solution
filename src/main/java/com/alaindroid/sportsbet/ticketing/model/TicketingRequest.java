package com.alaindroid.sportsbet.ticketing.model;

import com.alaindroid.sportsbet.model.Customer;

import java.util.List;

public record TicketingRequest(List<Customer> customers) {
}
