package com.alaindroid.sportsbet.orders.service;

import com.alaindroid.sportsbet.common.mapper.OrderRequestMapper;
import com.alaindroid.sportsbet.common.model.Ticket;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import com.alaindroid.sportsbet.ticketing.service.TicketingService;
import com.alaindroid.sportsbet.transaction.TransactionIdService;
import com.alaindroid.sportsbet.orders.model.OrderRequest;
import com.alaindroid.sportsbet.orders.model.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class OrderHandler {
    @Autowired
    private OrderRequestMapper requestMapper;
    @Autowired
    private TicketingService ticketingService;
    @Autowired
    private TransactionIdService transactionIdService;

    public OrderResponse createOrder(OrderRequest request) {
        TicketingRequest ticketingRequest = requestMapper.map(request);
        TicketingResponse ticketingResponse = ticketingService.getTickets(ticketingRequest);
        Comparator<Ticket> ticketComparator = Comparator.comparing(ticket -> ticket.ticketType().toString());
        ticketingResponse.tickets().sort(ticketComparator);
        int responseTransactionId = transactionIdService.getResponseTransactionId(request.transactionId());
        return new OrderResponse(responseTransactionId,
                ticketingResponse.tickets(),
                ticketingResponse.totalCost());
    }
}
