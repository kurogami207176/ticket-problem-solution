package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.model.Customer;
import com.alaindroid.sportsbet.model.TicketType;
import org.springframework.stereotype.Service;

@Service
public class CustomerCategoriserService {
    public TicketType getTicketType(Customer customer) {
        if (customer.age() >= 65) {
            return TicketType.SENIOR;
        } else if (customer.age() >= 18) {
            return TicketType.ADULT;
        } else if (customer.age() >= 11) {
            return TicketType.TEEN;
        } else {
            return TicketType.CHILDREN;
        }
    }
}
