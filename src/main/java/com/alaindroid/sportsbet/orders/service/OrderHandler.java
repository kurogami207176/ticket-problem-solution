package com.alaindroid.sportsbet.orders.service;

import com.alaindroid.sportsbet.api.controller.OrderApiDelegate;
import com.alaindroid.sportsbet.api.model.OrderRequest;
import com.alaindroid.sportsbet.api.model.OrderResponse;
import com.alaindroid.sportsbet.common.mapper.OrderRequestMapper;
import com.alaindroid.sportsbet.common.model.Ticket;
import com.alaindroid.sportsbet.orders.model.OrderDelegateRequest;
import com.alaindroid.sportsbet.orders.model.OrderDelegateResponse;
import com.alaindroid.sportsbet.ticketing.model.TicketingRequest;
import com.alaindroid.sportsbet.ticketing.model.TicketingResponse;
import com.alaindroid.sportsbet.ticketing.service.TicketingService;
import com.alaindroid.sportsbet.transaction.TransactionIdService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class OrderHandler implements OrderApiDelegate {
    @Autowired
    private OrderRequestMapper requestMapper;
    @Autowired
    private TicketingService ticketingService;
    @Autowired
    private TransactionIdService transactionIdService;

    @Override
    public ResponseEntity<OrderResponse> order(OrderRequest orderRequest) {
        OrderDelegateResponse response = createOrder(requestMapper.map(orderRequest));
        return new ResponseEntity<>(requestMapper.map(response), HttpStatus.OK);
    }

    public OrderDelegateResponse createOrder(OrderDelegateRequest request) {
        TicketingRequest ticketingRequest = requestMapper.map(request);
        TicketingResponse ticketingResponse = ticketingService.getTickets(ticketingRequest);
        Comparator<Ticket> ticketComparator = Comparator.comparing(ticket -> ticket.ticketType().toString());
        ticketingResponse.tickets().sort(ticketComparator);
        int responseTransactionId = transactionIdService.getResponseTransactionId(request.transactionId());
        return new OrderDelegateResponse(responseTransactionId,
                ticketingResponse.tickets(),
                ticketingResponse.totalCost());
    }
}
