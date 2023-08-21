package com.alaindroid.sportsbet.ticketing.service;

import com.alaindroid.sportsbet.model.Customer;
import com.alaindroid.sportsbet.model.TicketType;
import com.alaindroid.sportsbet.pricing.service.PricingService;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketingServiceTest {

    private TicketingService subject;
    private PricingService mockPricingService;
    private CustomerCategoriserService mockCustomerCategoriserService;
    private DiscountServices mockDiscountService;
    private static final Customer child = new Customer("", 1);
    private static final Customer adult = new Customer("", 44);
    private static final Customer teen = new Customer("", 15);
    private static final Customer senior = new Customer("", 99);


    @BeforeEach
    void init(@Mock PricingService pricingService,
              @Mock CustomerCategoriserService customerCategoriserService,
              @Mock DiscountServices discountServices) {
        subject = new TicketingService(pricingService, customerCategoriserService, discountServices);
        mockCustomerCategoriserService = customerCategoriserService;
        mockDiscountService = discountServices;
        mockPricingService = pricingService;
    }

    @Test
    void getTickets__OneEach__FourTickets() {
        // Given
        when(mockCustomerCategoriserService.getTicketType(eq(child)))
                .thenReturn(TicketType.CHILDREN);
        when(mockCustomerCategoriserService.getTicketType(eq(senior)))
                .thenReturn(TicketType.SENIOR);
        when(mockCustomerCategoriserService.getTicketType(eq(teen)))
                .thenReturn(TicketType.TEEN);
        when(mockCustomerCategoriserService.getTicketType(eq(adult)))
                .thenReturn(TicketType.ADULT);
        when(mockDiscountService.applyDiscounts(any()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(mockPricingService.getPrice(any()))
                .thenReturn(BigDecimal.ONE);
        TicketingRequest request = new TicketingRequest(Arrays.asList(child, teen, adult, senior));
        // When
        TicketingResponse response = subject.getTickets(request);

        // Then
        assertThat(response.totalCost())
                .isEqualByComparingTo(BigDecimal.valueOf(4));
        assertThat(response.tickets()).hasSize(4);
    }

    @Test
    void getTickets__None__NoTickets() {
        TicketingRequest request = new TicketingRequest(Collections.emptyList());
        TicketingResponse response = subject.getTickets(request);

        assertThat(response.totalCost())
                .isEqualByComparingTo(BigDecimal.valueOf(0));
        assertThat(response.tickets()).hasSize(0);
    }

    @Test
    void getTickets__TwoOfOne__OneTickets() {
        // Given
        when(mockCustomerCategoriserService.getTicketType(eq(child)))
                .thenReturn(TicketType.CHILDREN);
        when(mockDiscountService.applyDiscounts(any()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(mockPricingService.getPrice(any()))
                .thenReturn(BigDecimal.ONE);
        TicketingRequest request = new TicketingRequest(Arrays.asList(child, child));
        TicketingResponse response = subject.getTickets(request);

        assertThat(response.totalCost())
                .isEqualByComparingTo(BigDecimal.valueOf(2));
        assertThat(response.tickets()).hasSize(1);
    }

    private static void mockCustomerCategoriser(PricingService pricingService,
                                                CustomerCategoriserService customerCategoriserService,
                                                DiscountServices discountServices) {
        when(customerCategoriserService.getTicketType(eq(child)))
                .thenReturn(TicketType.CHILDREN);
        when(customerCategoriserService.getTicketType(eq(senior)))
                .thenReturn(TicketType.SENIOR);
        when(customerCategoriserService.getTicketType(eq(teen)))
                .thenReturn(TicketType.TEEN);
        when(customerCategoriserService.getTicketType(eq(adult)))
                .thenReturn(TicketType.ADULT);
        when(discountServices.applyDiscounts(any()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(pricingService.getPrice(any()))
                .thenReturn(BigDecimal.ONE);
    }

}