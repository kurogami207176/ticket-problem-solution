package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.model.Customer;
import com.alaindroid.sportsbet.model.TicketType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerCategoriserServiceTest {

    private CustomerCategoriserService subject = new CustomerCategoriserService();
    @Test
    void getTicketType__AgeMinimumSenior__Senior() {
        assertThat(subject.getTicketType(new Customer("", 65)))
                .isEqualByComparingTo(TicketType.SENIOR);
    }
    @Test
    void getTicketType__AgeMaxAdult__Adult() {
        assertThat(subject.getTicketType(new Customer("", 64)))
                .isEqualByComparingTo(TicketType.ADULT);
    }
    @Test
    void getTicketType__AgeMinAdult__Adult() {
        assertThat(subject.getTicketType(new Customer("", 18)))
                .isEqualByComparingTo(TicketType.ADULT);
    }
    @Test
    void getTicketType__AgeMaxTeen__Teen() {
        assertThat(subject.getTicketType(new Customer("", 17)))
                .isEqualByComparingTo(TicketType.TEEN);
    }
    @Test
    void getTicketType__AgeMinTeen__Teen() {
        assertThat(subject.getTicketType(new Customer("", 11)))
                .isEqualByComparingTo(TicketType.TEEN);
    }
    @Test
    void getTicketType__AgeMaxChild__Children() {
        assertThat(subject.getTicketType(new Customer("", 10)))
                .isEqualByComparingTo(TicketType.CHILDREN);
    }
}