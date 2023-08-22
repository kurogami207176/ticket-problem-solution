package com.alaindroid.sportsbet.ticketing.model;

import java.util.List;
import java.util.Objects;

public record CustomerCategories(List<CustomerRange> types) {
    public CustomerCategories {
        Objects.requireNonNull(types);
    }
}
