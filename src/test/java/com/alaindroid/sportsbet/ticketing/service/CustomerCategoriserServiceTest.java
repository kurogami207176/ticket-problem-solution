package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.common.model.Customer;
import com.alaindroid.sportsbet.common.model.TicketType;
import com.alaindroid.sportsbet.ticketing.model.CustomerCategories;
import com.alaindroid.sportsbet.ticketing.model.CustomerRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerCategoriserServiceTest {

    private CustomerCategoriserService subject;

    @BeforeEach
    void init(@Mock CustomerCategories customerCategories) {
        List<CustomerRange> ranges = Arrays.asList(
                new CustomerRange(TicketType.ADULT, 18),
                new CustomerRange(TicketType.SENIOR, 65),
                new CustomerRange(TicketType.TEEN, 11),
                new CustomerRange(TicketType.CHILDREN, 0)
        );
        when(customerCategories.types())
                .thenReturn(ranges);
        subject = new CustomerCategoriserService(customerCategories);
    }

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