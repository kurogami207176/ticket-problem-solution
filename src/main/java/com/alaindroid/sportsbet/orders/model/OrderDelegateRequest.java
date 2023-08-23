package com.alaindroid.sportsbet.orders.model;

import com.alaindroid.sportsbet.common.model.Customer;

import java.util.List;
import java.util.Objects;

public record OrderDelegateRequest(int transactionId, List<Customer> customers) {
    public OrderDelegateRequest {
        Objects.requireNonNull(customers, "customers cannot be null");
    }

}
