package com.alaindroid.sportsbet.transport.handler;

import com.alaindroid.sportsbet.mapper.OrderRequestMapper;
import com.alaindroid.sportsbet.mapper.OrderResponseMapper;
import com.alaindroid.sportsbet.model.Ticket;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import com.alaindroid.sportsbet.ticketing.service.TicketingService;
import com.alaindroid.sportsbet.transaction.TransactionIdService;
import com.alaindroid.sportsbet.transport.model.OrderRequest;
import com.alaindroid.sportsbet.transport.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;

@Service
public class OrderHandler {
    @Autowired
    private OrderRequestMapper requestMapper;
    @Autowired
    private OrderResponseMapper responseMapper;
    @Autowired
    private TicketingService ticketingService;
    @Autowired
    private TransactionIdService transactionIdService;

    public OrderResponse createOrder(OrderRequest request) {
        TicketingRequest ticketingRequest = requestMapper.map(request);
        TicketingResponse ticketingResponse = ticketingService.getPrice(ticketingRequest);
        Comparator<Ticket> ticketComparator = Comparator.comparing(ticket -> ticket.ticketType().toString());
        Collections.sort(ticketingResponse.tickets(), ticketComparator);
        int responseTransactionId = transactionIdService.getResponseTransactionId(request.transactionId());
        OrderResponse response = new OrderResponse(responseTransactionId,
                ticketingResponse.tickets(),
                ticketingResponse.totaCost());
        return response;
    }
}
