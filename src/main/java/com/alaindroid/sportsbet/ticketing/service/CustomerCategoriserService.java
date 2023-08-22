package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.common.model.Customer;
import com.alaindroid.sportsbet.common.model.TicketType;
import com.alaindroid.sportsbet.ticketing.model.CustomerCategories;
import com.alaindroid.sportsbet.ticketing.model.CustomerRange;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class CustomerCategoriserService {
    private CustomerCategories customerCategories;
    public TicketType getTicketType(Customer customer) {
        return customerCategories.types().stream()
                .filter(type -> type.minimumAge() <= customer.age())
                .min(Comparator.comparing(CustomerRange::minimumAge).reversed())
                .map(CustomerRange::type)
                .orElseThrow(() -> new RuntimeException("Bad"));
    }
}
