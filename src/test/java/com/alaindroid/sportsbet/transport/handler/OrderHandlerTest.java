package com.alaindroid.sportsbet.transport.handler;

import com.alaindroid.sportsbet.mapper.OrderRequestMapper;
import com.alaindroid.sportsbet.model.Ticket;
import com.alaindroid.sportsbet.model.TicketType;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import com.alaindroid.sportsbet.ticketing.service.TicketingService;
import com.alaindroid.sportsbet.transaction.TransactionIdService;
import com.alaindroid.sportsbet.transport.model.OrderRequest;
import com.alaindroid.sportsbet.transport.model.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderHandlerTest {
    private OrderHandler orderHandler;

    private TicketingRequest mockTicketRequest;
    private TicketingResponse mockTicketResponse;

    @BeforeEach
    void init(@Mock OrderRequestMapper requestMapper,
              @Mock TicketingService ticketingService,
              @Mock TransactionIdService transactionIdService) {
        orderHandler = new OrderHandler(requestMapper, ticketingService, transactionIdService);
        mockTicketRequest = mock(TicketingRequest.class);
        mockTicketResponse = mock(TicketingResponse.class);
        when(requestMapper.map(any()))
                .thenReturn(mockTicketRequest);
        when(ticketingService.getTickets(any()))
                .thenReturn(mockTicketResponse);
        when(transactionIdService.getResponseTransactionId(anyInt()))
                .thenReturn(1);

    }

    @Test
    void createOrder__TotalCost10__ReturnedTotalCost10() {
        OrderRequest orderRequest = Mockito.mock(OrderRequest.class);
        when(mockTicketResponse.totalCost())
                .thenReturn(BigDecimal.TEN);
        OrderResponse response = orderHandler.createOrder(orderRequest);
        assertThat(response.totaCost())
                .isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    void createOrder__UnOrderedTickets__ReturnOrderedTickets() {
        OrderRequest orderRequest = Mockito.mock(OrderRequest.class);
        when(mockTicketResponse.totalCost())
                .thenReturn(BigDecimal.TEN);
        Ticket ticketSenior = new Ticket(TicketType.SENIOR, 1, BigDecimal.ONE);
        Ticket ticketTeen = new Ticket(TicketType.TEEN, 1, BigDecimal.ONE);
        Ticket ticketAdult = new Ticket(TicketType.ADULT, 1, BigDecimal.ONE);
        Ticket ticketChild = new Ticket(TicketType.CHILDREN, 1, BigDecimal.ONE);
        when(mockTicketResponse.tickets())
                .thenReturn(Arrays.asList(ticketChild, ticketSenior, ticketTeen, ticketAdult));
        OrderResponse response = orderHandler.createOrder(orderRequest);
        assertThat(response.tickets().get(0).ticketType())
                .isEqualByComparingTo(TicketType.ADULT);
        assertThat(response.tickets().get(1).ticketType())
                .isEqualByComparingTo(TicketType.CHILDREN);
        assertThat(response.tickets().get(2).ticketType())
                .isEqualByComparingTo(TicketType.SENIOR);
        assertThat(response.tickets().get(3).ticketType())
                .isEqualByComparingTo(TicketType.TEEN);
    }
}