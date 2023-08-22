package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.common.model.Ticket;
import com.alaindroid.sportsbet.common.model.TicketType;
import com.alaindroid.sportsbet.ticketing.model.DiscountConfiguration;
import com.alaindroid.sportsbet.ticketing.model.TicketDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServicesTest {

    private DiscountServices discountServices;

    @BeforeEach
    void init(@Mock DiscountConfiguration discountConfiguration) {
        Map<TicketType, TicketDiscount> tickets = new HashMap<>();
        tickets.put(TicketType.CHILDREN, new TicketDiscount(3, BigDecimal.valueOf(0.25)));
        when(discountConfiguration.tickets())
                .thenReturn(tickets);
        discountServices = new DiscountServices(discountConfiguration);
    }

    @Test
    void applyDiscounts__Adult__NoDiscount() {
        Ticket ticket = new Ticket(TicketType.ADULT, 99, BigDecimal.TEN);
        Ticket result = discountServices.applyDiscounts(ticket);
        assertThat(ticket).isSameAs(result);
    }

    @Test
    void applyDiscounts__Senior__NoDiscount() {
        Ticket ticket = new Ticket(TicketType.SENIOR, 99, BigDecimal.TEN);
        Ticket result = discountServices.applyDiscounts(ticket);
        assertThat(ticket).isSameAs(result);
    }

    @Test
    void applyDiscounts__Teen__NoDiscount() {
        Ticket ticket = new Ticket(TicketType.TEEN, 99, BigDecimal.TEN);
        Ticket result = discountServices.applyDiscounts(ticket);
        assertThat(ticket).isSameAs(result);
    }

    @Test
    void applyDiscounts__Child_MaxNoDiscount__NoDiscount() {
        Ticket ticket = new Ticket(TicketType.CHILDREN, 2, BigDecimal.TEN);
        Ticket result = discountServices.applyDiscounts(ticket);
        assertThat(ticket).isSameAs(result);
    }

    @Test
    void applyDiscounts__Child_MinWithDiscount__NoDiscount() {
        Ticket ticket = new Ticket(TicketType.CHILDREN, 3, BigDecimal.TEN);
        Ticket result = discountServices.applyDiscounts(ticket);
        assertThat(ticket).isNotSameAs(result);
        assertThat(result.totalCost()).isEqualByComparingTo(BigDecimal.valueOf(7.5));
    }
}