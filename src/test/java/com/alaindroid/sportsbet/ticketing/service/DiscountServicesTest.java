package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.model.Ticket;
import com.alaindroid.sportsbet.model.TicketType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiscountServicesTest {

    private DiscountServices discountServices = new DiscountServices();

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